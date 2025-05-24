# Princípio da Substituição de Liskov / Liskov Substitution Principle (LSP)

Segundo Barbara Liskov, criadora do princípio, o Liskov Substitution Principle afirma que “objetos de uma classe derivada devem ser substituíveis pelos objetos de sua classe base sem afetar a corretude do programa”. Em outras palavras, qualquer instância de uma subclasse deve manter o comportamento esperado da superclasse. A violação do LSP ocorre quando o uso de uma subclasse quebra a lógica do código cliente de forma silenciosa e inesperada.

Segundo Marco Túlio Valente, em Engenharia de Software Moderna, o LSP é essencial para garantir a integridade dos contratos estabelecidos pelas superclasses. Ele destaca que respeitar o princípio evita “surpresas” no comportamento do sistema ao usar polimorfismo, preservando a previsibilidade e consistência do código. Além disso, Valente reforça que a violação do LSP está ligada à quebra de abstrações, gerando acoplamentos indevidos entre componentes e reduzindo a confiabilidade do software.

Um exemplo pode ser encontrado no repositório [LucasDiogo96/S.O.L.I.D](https://github.com/LucasDiogo96/S.O.L.I.D/tree/main/3%20-%20Liskov%20Substitution) (escrito em C#). No código original, temos:

```csharp
public class Apple
{
    public virtual string GetColor()
    {
        return "Red";
    }
}

public class Orange : Apple
{
    public override string GetColor()
    {
        return "Orange";
    }
}

Apple fruit = new Orange();
Console.WriteLine(fruit.GetColor()); // Saída: "Orange"
```

Embora compilável, esse código viola o LSP. Ao criar uma laranja como se fosse uma maçã (`Apple fruit = new Orange()`), ocorre uma inversão de semântica: espera-se uma maçã vermelha, mas obtém-se uma laranja. Isso compromete o contrato original e pode gerar falhas lógicas.

A refatoração corrige esse problema por meio da introdução de uma abstração real:

```csharp
public abstract class Fruit
{
    public abstract string GetColor();
}

public class Apple : Fruit
{
    public override string GetColor()
    {
        return "Red";
    }
}

public class Orange : Fruit
{
    public override string GetColor()
    {
        return "Orange";
    }
}

Fruit fruit = new Orange();
Console.WriteLine(fruit.GetColor()); // Saída: "Orange"
```

Agora, `Apple` e `Orange` herdam de uma classe base abstrata `Fruit`, que define claramente o contrato `GetColor()`. Com isso, qualquer substituição entre subtipos respeita o comportamento esperado, garantindo que o sistema continue funcionando corretamente, conforme preconizado pelo LSP.

Principais benefícios do LSP:

* Segurança polimórfica: Subtipos podem ser usados com segurança no lugar de suas superclasses.
* Manutenção de contrato: Evita que extensões violem regras estabelecidas pelas abstrações.
* Confiabilidade: Reduz o risco de erros ocultos decorrentes de substituições impróprias.

O LSP é essencial para preservar a integridade e a previsibilidade em sistemas orientados a objetos, especialmente quando se utiliza herança e polimorfismo extensivamente.

---

**Referências:**

* Barbara Liskov. [Data Abstraction and Hierarchy](https://www.cs.tufts.edu/~nr/cs257/archive/barbara-liskov/data-abstraction-and-hierarchy.pdf) (1987)
* [Wikipedia - Liskov Substitution Principle](https://pt.wikipedia.org/wiki/Princ%C3%ADpio_da_substitui%C3%A7%C3%A3o_de_Liskov)
* [Marco Túlio Valente - Engenharia de Software Moderna](https://engsoftmoderna.info/cap5.html)
* [Exemplo de código no GitHub](https://github.com/LucasDiogo96/S.O.L.I.D/tree/main/3%20-%20Liskov%20Substitution)
