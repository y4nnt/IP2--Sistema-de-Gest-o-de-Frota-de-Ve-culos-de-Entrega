    # IP2--Sistema-de-Gest-o-de-Frota-de-Ve-culos-de-Entrega

# Descrição
Este sistema tem como objetivo gerenciar a frota de veículos de uma empresa de entregas, incluindo cadastro de veículos, motoristas, rotas e acompanhamento das entregas. Ele permite organizar veículos e motoristas, atribuir rotas, monitorar status de entregas e gerar relatórios de desempenho da frota.

O sistema deve controlar a disponibilidade de veículos e motoristas, registrar manutenção, acompanhar o progresso das entregas em tempo real e gerar alertas de atrasos ou problemas. Relatórios detalhados por veículo, motorista, rota ou período devem estar disponíveis para análise operacional.

## Requisitos Funcionais
### 1. Gerenciamento de Veículos
REQ01: Permitir cadastro de veículos com placa, modelo, capacidade de carga e status (disponível, em manutenção, em uso).
REQ02: Registrar histórico de manutenções e problemas mecânicos.
REQ03: Atualizar status do veículo automaticamente conforme sua utilização ou manutenção.

### 2. Gerenciamento de Motoristas
REQ04: Permitir cadastro de motoristas com dados pessoais, CNH, disponibilidade e histórico de entregas.
REQ05: Atribuir motoristas a veículos e rotas específicas.
REQ06: Registrar pausas, férias e ausências dos motoristas.

### 3. Gerenciamento de Rotas
REQ07: Permitir criação de rotas com origem, destino, distância, tempo estimado e pontos de parada.
REQ08: Associar rotas a veículos e motoristas disponíveis.
REQ09: Atualizar rotas em tempo real em caso de alterações de tráfego ou problemas.

### 4. Registro de Entregas
REQ10: Permitir registro de entregas vinculadas a um veículo, motorista e rota.
REQ11: Atualizar status da entrega (pendente, em trânsito, entregue, atrasada, cancelada).
REQ12: Permitir inclusão de observações ou problemas ocorridos durante a entrega.
REQ13: Gerar alertas automáticos para entregas atrasadas.

### 5. Controle de Disponibilidade
REQ14: Bloquear veículos indisponíveis (em manutenção, fora de serviço) para novas rotas.
REQ15: Impedir alocação de motorista ausente ou com horário conflitante.
REQ16: Atualizar automaticamente a disponibilidade de motoristas e veículos após cada entrega concluída.

### 6. Relatórios e Estatísticas
REQ17: Gerar relatório de entregas por veículo, motorista, rota ou período.
REQ18: Relatório de quilometragem e uso da frota.
REQ19: Relatório de desempenho de motoristas (entregas concluídas, atrasos, incidentes).
REQ20: Permitir exportação de relatórios em PDF e CSV, com filtros e agrupamentos.

### 7. Alertas e Notificações
REQ21: Enviar alertas de entregas atrasadas ou problemas em tempo real.
REQ22: Notificar motoristas sobre alterações de rota ou manutenção do veículo.
REQ23: Alertas para veículos próximos da manutenção programada.

### 8. Regras e Restrições
REQ24: Não permitir atribuição de rota a veículo ou motorista indisponível.
REQ25: Bloquear exclusão de entregas em andamento.
REQ26: Garantir que veículos em manutenção não sejam alocados.
REQ27: Não permitir cadastro de entrega sem rota, veículo e motorista associados.

## Link do github
https://github.com/y4nnt/IP2--Sistema-de-Gest-o-de-Frota-de-Ve-culos-de-Entrega.git

## Integrantes
* Yann Tavares de Santana - yann.tavares@ufrpe.br - yanntavares123@gmail.com
* Rian Guilherme Camara Fernandes - rian.guilherme@ufrpe.br - riangui0305@gmail.com
* Lucas Gabriel da Silva Andrade - lucas.silvaandrade@ufrpe.br - lucasandrade16.2006@gmail.com
* João Marcelo Alves Martins - joao.marcelo@ufrpe.br - joaomarceloalves1909@gmail.com
* Nícolas Gabriel Nunes Marques - nicolas.gabriel@ufrpe.br - nicolasg22132@gmail.com

## Diagrama de classes
![UML Sistema de Gestão de Frota de Veículos de Entrega](https://github.com/user-attachments/assets/c0bf0ef1-7679-4f9c-b89b-85197b2131dd)

## Modelo navegacional
![Modelo Navegacional](https://github.com/user-attachments/assets/830337b0-2597-4b4d-a9a3-19ac38ba958c)

### Documentos originais
[UML](https://github.com/user-attachments/files/23731240/UML.Sistema.de.Gestao.de.Frota.de.Veiculos.de.Entrega.drawio.pdf)

[Modelo Navegacional](https://github.com/user-attachments/files/23731242/Modelo.Navegacional.drawio.pdf)







