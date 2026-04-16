# Thread Java

Simulação concorrente de uma cadeia de produção e comercialização de veículos em Java.

## Relatório do funcionamento atual

O projeto está funcional para execução local (mesmo processo/JVM), com produção, distribuição para lojas e compra por clientes usando threads e semáforos.

### Fluxo implementado hoje

1. A aplicação inicia em `LojaMain`.
2. É criado um estoque de peças com capacidade de 500 (`Semaphore(500)`).
3. É criada uma esteira de peças com até 5 acessos simultâneos (`Semaphore(5)`).
4. São iniciadas 4 estações de produção.
5. Cada estação cria 5 funcionários em estrutura circular de ferramentas.
6. Funcionários produzem veículos e inserem na esteira da fábrica (buffer circular de 40 posições).
7. 3 lojas (threads) removem veículos da esteira da fábrica e inserem em sua própria esteira circular.
8. 20 clientes (threads) escolhem lojas aleatoriamente e compram veículos, salvando na garagem (buffer local do cliente).

### Concorrência e sincronização

- Exclusivamente com semáforos, conforme restrição do trabalho.
- Controle de buffer produtor/consumidor nas esteiras da fábrica e das lojas.
- Controle de ferramentas adjacentes (adaptação do jantar dos filósofos).
- Estratégia para evitar deadlock entre funcionários: um funcionário inverte a ordem de aquisição das ferramentas.

### Logs atualmente gerados

- `LOG PRODUCAO` (fábrica):
	- id do veículo
	- cor (alternando RED/GREEN/BLUE)
	- tipo (alternando SUV/SEDAN)
	- id da estação
	- id do funcionário
	- posição na esteira da fábrica
- `LOG RECEBIMENTO LOJA` (loja): veículo recebido da fábrica
- `LOG VENDA LOJA` (loja): transferência para a esteira da loja
- `LOG VENDA CLIENTE` (cliente): compra final e armazenamento na garagem

## Status em relação aos requisitos da atividade

### Requisitos atendidos

- Fábrica com estoque limitado a 500 peças.
- Esteira de peças com 5 solicitações simultâneas.
- 4 estações produtoras.
- 5 funcionários por estação em estrutura circular.
- Ferramentas adjacentes com sincronização por semáforo.
- Esteira circular da fábrica com capacidade 40.
- 3 lojas ativas.
- 20 clientes em threads com escolha aleatória de loja e compra aleatória de múltiplos veículos.
- Clientes com garagem para armazenar veículos comprados.

### Requisitos parcialmente atendidos ou pendentes

- Arquitetura distribuída (entidades remotas): **pendente**.
	- No momento, tudo roda localmente na mesma JVM.
	- Ainda não há Socket/RMI implementado entre fábrica e lojas.
- Modelo cliente-servidor entre fábrica e lojas: **pendente**.
- Log de “venda da fábrica para loja” no lado da fábrica com ID da loja e posição da esteira da loja: **parcial**.
	- Informações existem no fluxo, mas hoje os logs principais dessa etapa estão sendo emitidos no contexto da loja.

## Estrutura de pastas

```text
src/
	main/
		java/
			br/com/threadjava/
				app/
					FabricaMain.java
					LojaMain.java
				client/
					Cliente.java
				factory/
					Estacao.java
					EsteiraFabrica.java
					Funcionario.java
					GeradorVeiculo.java
				model/
					Veiculo.java
				store/
					EsteiraLoja.java
					Loja.java
```

## Documentação técnica das classes

### `app`

- `FabricaMain`: inicia apenas o cenário da fábrica (produção).
- `LojaMain`: inicia cenário completo (fábrica + lojas + clientes).

### `factory`

- `Estacao`: agrupa funcionários e ferramentas da estação.
- `Funcionario`: thread produtora, consome peça, pega ferramentas e fabrica veículo.
- `GeradorVeiculo`: gera ID sequencial e alterna cor/tipo com exclusão mútua.
- `EsteiraFabrica`: buffer circular com semáforos para produção/consumo.

### `store`

- `Loja`: thread consumidora da esteira da fábrica e produtora da esteira da loja.
- `EsteiraLoja`: buffer circular da loja para atender clientes.

### `client`

- `Cliente`: thread consumidora da esteira de uma loja escolhida aleatoriamente.

### `model`

- `Veiculo`: entidade de dados da cadeia produtiva.

## Como compilar e executar

### 1) Compilar

```powershell
javac -d out (Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

### 2) Rodar somente fábrica

```powershell
java -cp out br.com.threadjava.app.FabricaMain
```

### 3) Rodar simulação completa

```powershell
java -cp out br.com.threadjava.app.LojaMain
```

## Como validar rapidamente se está funcionando

- Ao rodar `FabricaMain`, devem aparecer vários `LOG PRODUCAO`.
- Ao rodar `LojaMain`, devem aparecer logs em cadeia:
	- `LOG PRODUCAO`
	- `LOG RECEBIMENTO LOJA`
	- `LOG VENDA LOJA`
	- `LOG VENDA CLIENTE`
- Com o tempo, o estoque de 500 peças se esgota e os funcionários informam espera/esgotamento (comportamento esperado do cenário atual).

## Observações para apresentação/defesa

- O código já demonstra bem os conceitos de concorrência e sincronização com semáforos.
- Para fechar 100% dos requisitos da atividade, o próximo passo é implementar comunicação distribuída (Socket ou RMI) entre fábrica e lojas.