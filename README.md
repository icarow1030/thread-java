# Thread Java

Projeto de simulação concorrente da cadeia de produção e comercialização de veículos.

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

## Executar

Compilar:

```powershell
javac -d out (Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

Executar simulação completa:

```powershell
java -cp out br.com.threadjava.app.LojaMain
```