package com.gestaoentregas.FXController;

import com.gestaoentregas.RelatorioEntregaDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

@Component
public class RelatorioController {

    // Filtros
    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFim;
    @FXML private ComboBox<String> cbMotorista;
    @FXML private ComboBox<String> cbVeiculo;

    // Labels de Resumo (KPIs)
    @FXML private Label lblTotalEntregas;
    @FXML private Label lblTaxaSucesso;

    // Tabela - Correção dos Tipos Genéricos <DTO, TipoDoDado>
    @FXML private TableView<RelatorioEntregaDTO> tabelaRelatorio;
    @FXML private TableColumn<RelatorioEntregaDTO, LocalDate> colData;
    @FXML private TableColumn<RelatorioEntregaDTO, String> colMotorista;
    @FXML private TableColumn<RelatorioEntregaDTO, String> colVeiculo;
    @FXML private TableColumn<RelatorioEntregaDTO, String> colStatus;
    // Adicione a coluna KM se tiver no FXML
    @FXML private TableColumn<RelatorioEntregaDTO, Double> colKm;

    @FXML private Button btnVoltar;

    private final ApplicationContext context;

    public RelatorioController (ApplicationContext context){
        this.context = context;
    }
    @FXML
    public void initialize() {
        // Vincula as colunas aos atributos do DTO
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
        colVeiculo.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Se tiver a coluna KM:
        // colKm.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
    }

    @FXML
    private void handleFiltrar() {
        LocalDate inicio = dpInicio.getValue();
        LocalDate fim = dpFim.getValue();
        String motorista = cbMotorista.getValue();

        System.out.println("Filtrando de " + inicio + " até " + fim);

        // AQUI: Chame o seu Service para buscar os dados.
        // Exemplo fictício:
        // List<RelatorioEntregaDTO> dados = service.buscarRelatorio(inicio, fim, motorista);
        // tabelaRelatorio.setItems(FXCollections.observableArrayList(dados));
    }

    @FXML
    private void handleExportarPDF() {
        // 1. Abre janela para o usuário escolher onde salvar
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório em PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos PDF", "*.pdf"));

        // Pega a janela atual para abrir o dialog em cima dela
        Stage stage = (Stage) tabelaRelatorio.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // O usuário escolheu um arquivo, vamos gerar
            try {
                // Criação do Documento PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                // Título
                com.lowagie.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                Paragraph titulo = new Paragraph("Relatório de Entregas", fontTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);
                document.add(new Paragraph(" ")); // Pula linha

                // Tabela no PDF (4 colunas conforme seu código)
                PdfPTable table = new PdfPTable(4);
                table.addCell("Data");
                table.addCell("Motorista");
                table.addCell("Veículo");
                table.addCell("Status");

                // Pega os itens que estão VISÍVEIS na tabela do JavaFX
                List<RelatorioEntregaDTO> itensAtuais = tabelaRelatorio.getItems();

                for (RelatorioEntregaDTO entrega : itensAtuais) {
                    // Cuidado com nulls ao converter para String
                    table.addCell(entrega.getData() != null ? entrega.getData().toString() : "");
                    table.addCell(entrega.getNomeMotorista() != null ? entrega.getNomeMotorista() : "");
                    table.addCell(entrega.getPlacaVeiculo() != null ? entrega.getPlacaVeiculo() : "");
                    table.addCell(entrega.getStatus() != null ? entrega.getStatus().toString() : "");
                }

                document.add(table);


                document.close();

                System.out.println("PDF gerado com sucesso em: " + file.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/MenuAdm.fxml", "Menu do Adm");
    }

    @FXML
    private void handleExportarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório em CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));
        fileChooser.setInitialFileName("relatorio_entregas.csv");

        Stage stage = (Stage) tabelaRelatorio.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.ISO_8859_1);
                 CSVPrinter csvPrinter = new CSVPrinter(writer,
                         CSVFormat.EXCEL.withDelimiter(';') // Ponto e vírgula é melhor para Excel BR
                                 .withHeader("Data", "Motorista", "Veículo", "Status"))) {

                List<RelatorioEntregaDTO> itens = tabelaRelatorio.getItems();

                for (RelatorioEntregaDTO item : itens) {
                    csvPrinter.printRecord(
                            item.getData(),
                            item.getNomeMotorista(),
                            item.getPlacaVeiculo(),
                            item.getStatus()
                    );
                }

                csvPrinter.flush();
                mostrarAlerta("Sucesso", "Arquivo CSV exportado com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Erro", "Não foi possível salvar o arquivo: " + e.getMessage());
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void abrirTela(javafx.event.ActionEvent event, String fxmlPath, String titulo) {
        try {
            // 1. Carrega o Loader da NOVA tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            // 2. Prepara a nova janela
            Stage stageNovo = new Stage();
            stageNovo.setScene(new Scene(root));
            stageNovo.setTitle(titulo);
            stageNovo.setResizable(false);

            // 3. Mostra a nova janela
            stageNovo.show();

            // 4. FECHA A JANELA ANTIGA (Só chega aqui se a nova abriu sem erros)
            // Pegamos a janela atual através do botão que foi clicado
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro crítico ao abrir a tela: " + fxmlPath);
            // Aqui você pode mostrar um Alert de erro para o usuário se quiser
        }
    }
}