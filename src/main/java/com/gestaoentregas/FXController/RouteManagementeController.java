package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Rota;

public class RouteManagementController {

    // Componentes FXML da Tabela de Rotas
    @FXML private TableView<Rota> routesTable;
    // Colunas mapeadas do FXML: routeIdColumn, driverColumn, statusColumn
    @FXML private TableColumn<Rota, Long> routeIdColumn;
    @FXML private TableColumn<Rota, String> driverColumn;
    @FXML private TableColumn<Rota, String> statusColumn;

    // Botões de Ação
    @FXML private Button newRouteButton;
    @FXML private Button optimizeButton;
    @FXML private Button deleteButton;
    @FXML private Button viewDetailsButton;

    // Lista de dados para a tabela
    private ObservableList<Rota> routeData = FXCollections.observableArrayList();

    /**
     * Método de inicialização chamado após o carregamento do FXML.
     */
    @FXML
    public void initialize() {
        // 1. Configurar as colunas da Tabela de Rotas
        // O nome da propriedade deve ser o nome do método 'get' no modelo Rota (ex: getId -> "id")
        routeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        // Usamos "motorista" no modelo Rota para mapear driverColumn do FXML
        driverColumn.setCellValueFactory(new PropertyValueFactory<>("motorista"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Coluna extra (não definida no FXML, mas útil):
        // TableColumn<Rota, String> vehicleColumn = new TableColumn<>("Veículo");
        // vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        // routesTable.getColumns().add(vehicleColumn);

        // 2. Carregar dados de exemplo (MOCK DATA)
        loadMockData();

        // 3. Atribuir dados à tabela
        routesTable.setItems(routeData);
    }

    /**
     * Carrega dados fictícios para demonstração.
     */
    private void loadMockData() {
        routeData.add(new Rota(1L, "Rota Norte A", "João Silva", "ABC-1234", "Em Andamento"));
        routeData.add(new Rota(2L, "Rota Sul B", "Maria Santos", "DEF-5678", "Pendente"));
        routeData.add(new Rota(3L, "Rota Leste C", "Carlos Pereira", "GHI-9012", "Concluída"));
    }

    // --- Métodos de Ação para Rotas (Mapeados do FXML) ---

    @FXML
    private void handleNewRoute(ActionEvent event) {
        // Lógica para abrir a tela de criação de nova rota (seleção de motorista, veículo e pontos de entrega)
        showInformation("Criar Nova Rota", "Iniciando formulário para criação de nova rota.");
    }

    @FXML
    private void handleOptimizeRoute(ActionEvent event) {
        Rota selectedRoute = routesTable.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
            // Lógica para chamar o algoritmo de otimização (Travelling Salesman Problem ou similar)
            showInformation("Otimização de Rota", "Otimizando a sequência de paradas para a rota ID: " + selectedRoute.getId());
        } else {
            showError("Erro", "Selecione uma rota para otimizar.");
        }
    }

    @FXML
    private void handleDeleteRoute(ActionEvent event) {
        Rota selectedRoute = routesTable.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
            // Lógica para confirmar e excluir a rota
            routeData.remove(selectedRoute);
            showInformation("Excluir Rota", "Rota ID " + selectedRoute.getId() + " excluída com sucesso.");
        } else {
            showError("Erro", "Selecione uma rota para exclusão.");
        }
    }

    @FXML
    private void handleViewRouteDetails(ActionEvent event) {
        Rota selectedRoute = routesTable.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
            // Lógica para carregar o mapa e os detalhes dos pontos de entrega no painel direito
            showInformation("Detalhes da Rota", "Carregando visualização detalhada para Rota ID: " + selectedRoute.getId());
        } else {
            showError("Erro", "Selecione uma rota para visualizar os detalhes.");
        }
    }

    // --- Métodos de Utilidade para Mensagens (usando JavaFX Alert) ---
    private void showInformation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}