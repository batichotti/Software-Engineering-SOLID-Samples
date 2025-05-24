# Princípio da Inversão de Dependência / Dependency Inversion Principle (DIP)

O Princípio da Inversão de Dependência orienta que “módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações”. Em outras palavras, o DIP recomenda que as classes estabeleçam dependências com interfaces (ou abstrações), e não com implementações concretas. Isso torna o sistema mais flexível, desacoplado e fácil de estender.

Segundo Marco Túlio Valente, o DIP pode ser interpretado como um chamado para “preferir interfaces a classes”. Ele explica: “quando um cliente se acopla a uma interface, ele fica imune a mudanças na implementação dessa interface”. Isso significa que se o comportamento de uma classe concreta mudar (ou for substituído por outra), os clientes que dependem apenas da abstração continuarão funcionando normalmente. Assim, o sistema adquire maior estabilidade diante de alterações.

Um exemplo claro de violação do DIP está no repositório [mikeknep/SOLID](https://github.com/mikeknep/SOLID/blob/main/dependency_inversion), onde a classe `WeatherTracker` depende diretamente das classes concretas `Phone` e `Emailer`:

```java
public class WeatherTracker {
    String currentConditions;
    Phone phone;
    Emailer emailer;

    public WeatherTracker() {
        phone = new Phone();
        emailer = new Emailer();
    }

    public void setCurrentConditions(String weatherDescription) {
        this.currentConditions = weatherDescription;
        if (weatherDescription == "rainy") {
            String alert = phone.generateWeatherAlert(weatherDescription);
            System.out.print(alert);
        }
        if (weatherDescription == "sunny") {
            String alert = emailer.generateWeatherAlert(weatherDescription);
            System.out.print(alert);
        }
    }
}
```

Neste cenário, `WeatherTracker` está fortemente acoplada às implementações de envio de alerta. Isso dificulta a substituição ou extensão desses comportamentos — por exemplo, para incluir um novo canal de notificação — sem modificar diretamente a classe.

A versão correta aplica o DIP introduzindo uma abstração `Notifier`, com a qual `WeatherTracker` se relaciona:

```java
public class WeatherTracker {
    String currentConditions;

    public void setCurrentConditions(String weatherDescription) {
        this.currentConditions = weatherDescription;
    }

    public void notify(Notifier notifier) {
        notifier.alertWeatherConditions(currentConditions);
    }
}
```

Com essa abordagem, `WeatherTracker` não conhece mais os detalhes de `Phone`, `Emailer` ou qualquer outro tipo de notificação. Basta que a classe concreta implemente a interface `Notifier`. Isso promove baixo acoplamento, alta flexibilidade e maior reutilização de código. Novos canais podem ser adicionados sem impactar o funcionamento da classe principal.

Marco Túlio também exemplifica o princípio com a seguinte situação: suponha que exista uma interface `I` e duas implementações concretas `C1` e `C2`. Um cliente deve se acoplar à `I`, e não a `C1` ou `C2`, para garantir estabilidade frente a mudanças nas implementações. Assim, o cliente continua funcionando corretamente mesmo que a lógica interna de `C1` mude ou seja substituída por `C2`.

Principais benefícios do DIP:

* Desacoplamento: Reduz dependências diretas entre classes, facilitando substituições.
* Testabilidade: Interfaces permitem injeção de dependências e uso de mocks/stubs.
* Flexibilidade: Novas implementações podem ser adicionadas sem alterar o código cliente.
* Manutenção facilitada: Modificações locais não afetam módulos de alto nível.

O DIP é essencial para sistemas extensíveis e robustos, promovendo uma arquitetura orientada a abstrações, não a detalhes de implementação.

---

**Referências:**

* Robert C. Martin – [Agile Software Development, Principles, Patterns, and Practices](https://dl.ebooksworld.ir/motoman/Pearson.Agile.Software.Development.Principles.Patterns.and.Practices.www.EBooksWorld.ir.pdf)
* [Wikipedia – Dependency Inversion Principle](https://pt.wikipedia.org/wiki/Princ%C3%ADpio_da_invers%C3%A3o_de_depend%C3%AAncia)
* Marco Túlio Valente – *Engenharia de Software Moderna* ([Capítulo 5](https://engsoftmoderna.info/cap5.html))
* [Exemplo de código no GitHub](https://github.com/mikeknep/SOLID/blob/main/dependency_inversion/bad/src/WeatherTracker.java)
