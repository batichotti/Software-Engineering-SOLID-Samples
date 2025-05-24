# Princípio da Segregação de Interfaces / Interface Segregation Principle (ISP)

O Princípio da Segregação de Interfaces afirma que “nenhum cliente deve ser forçado a depender de métodos que não utiliza”. Essa diretriz, integrante dos princípios SOLID, defende que interfaces devem ser pequenas, específicas e altamente coesas. Interfaces muito amplas ou genéricas acabam impondo dependências desnecessárias aos clientes, comprometendo a flexibilidade e clareza do design.

Segundo Marco Túlio Valente, no livro Engenharia de Software Moderna, o ISP pode ser entendido como uma aplicação prática do conceito de coesão no contexto de interfaces. Ele ainda ressalta: “o princípio define que interfaces têm que ser pequenas, coesas e, mais importante ainda, específicas para cada tipo de cliente.” Ao fazer isso, evita-se o acoplamento desnecessário entre classes que, embora relacionadas, exercem responsabilidades diferentes. Valente ainda aponta que uma violação comum ocorre quando uma interface reúne métodos que pertencem a contextos distintos, forçando classes a implementar operações que jamais utilizarão.

Um exemplo didático está presente no repositório [mikeknep/SOLID](https://github.com/mikeknep/SOLID/blob/main/interface_segregation/bad/src/Penguin.java), onde a classe `Penguin` implementa a interface `Bird`, que define métodos como `fly()` e `swim()`:

```java
public class Penguin implements Bird {
    String currentLocation;
    int numberOfFeathers;

    public Penguin(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }

    public void molt() {
        this.numberOfFeathers -= 1;
    }

    public void fly() {
        throw new UnsupportedOperationException();
    }

    public void swim() {
        this.currentLocation = "in the water";
    }
}
```

Neste cenário, o método `fly()` não faz sentido para pinguins, que são aves não voadoras. Ainda assim, a classe é obrigada a implementá-lo, o que leva à introdução de uma exceção — um claro sintoma de violação do ISP. O cliente está dependendo de um método que não usa.

A refatoração adequada consiste na separação da interface `Bird` em interfaces mais específicas e coesas, como `SwimmingCreature` e `FeatheredCreature`:

```java
public class Penguin implements SwimmingCreature, FeatheredCreature {
    String currentLocation;
    int numberOfFeathers;

    public Penguin(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }

    public void swim() {
        this.currentLocation = "in the water";
    }

    public void molt() {
        this.numberOfFeathers -= 4;
    }
}
```

Com isso, `Penguin` implementa apenas os métodos relevantes ao seu comportamento, respeitando o ISP e promovendo um design limpo, claro e robusto.

Marco Túlio Valente também exemplifica esse princípio com uma interface `Funcionario` contendo métodos distintos: `(1)` retornar salário, `(2)` retornar contribuição ao FGTS, e `(3)` retornar número de matrícula SIAPE. Tal interface força funcionários públicos a lidar com o FGTS (que não possuem), e celetistas a lidar com SIAPE (que não possuem). A solução seria dividir a interface em `FuncionarioCLT` e `FuncionarioPublico`, cada uma com suas responsabilidades específicas.

Principais benefícios do ISP:

* Alta coesão: Interfaces representam um único conceito ou responsabilidade.
* Baixo acoplamento: Clientes dependem apenas do que realmente utilizam.
* Facilidade de manutenção e evolução: Mudanças impactam menos componentes do sistema.

O ISP reforça a importância de projetar interfaces pensando na real necessidade dos consumidores. Interfaces bem definidas resultam em sistemas mais adaptáveis, robustos e fáceis de entender.

---

**Referências:**

* Robert C. Martin – [Agile Software Development, Principles, Patterns, and Practices](https://dl.ebooksworld.ir/motoman/Pearson.Agile.Software.Development.Principles.Patterns.and.Practices.www.EBooksWorld.ir.pdf)
* [Wikipedia – Interface Segregation Principle](https://pt.wikipedia.org/wiki/Princ%C3%ADpio_da_segrega%C3%A7%C3%A3o_de_interface)
* Marco Túlio Valente – *Engenharia de Software Moderna* ([Capítulo 5](https://engsoftmoderna.info/cap5.html))
* [Exemplo de código no GitHub](https://github.com/mikeknep/SOLID/blob/main/interface_segregation/bad/src/Penguin.java)
