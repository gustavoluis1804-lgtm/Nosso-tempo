# Nosso Tempo

Aplicativo Android com janela flutuante arrastável que mostra, em tempo real, quanto tempo passou desde **12/09/2026 às 16:43** no fuso de São Paulo.

## O contador mostra

- anos, meses e dias de calendário;
- horas, minutos e segundos;
- atualização a cada segundo;
- avisos quando faltarem 10 e 5 dias para cada aniversário mensal;
- tela comemorativa inteira no dia 12 de cada mês, às 16:43;
- mensagem anual especial quando completar um ano inteiro.

## Como gerar o APK pelo GitHub

1. Envie toda esta pasta para um repositório no GitHub.
2. Abra **Actions** e selecione **Gerar APK**.
3. Toque em **Run workflow**.
4. Ao terminar, baixe o arquivo **NossoTempo-APK** em *Artifacts*.
5. Extraia o ZIP e instale `app-debug.apk` no Android.

## Como usar

Abra **Nosso Tempo**, autorize a janela flutuante, os avisos e o alerta em tela inteira. Depois toque em **INICIAR CONTADOR**. A janela pode ser arrastada para qualquer parte da tela.

## Observação

O Android exige uma notificação pequena e permanente enquanto uma janela flutuante funciona em segundo plano. Em versões recentes, a tela inteira depende da permissão especial de alertas. Aplicativos comuns não podem substituir o relógio nativo da tela de bloqueio.
