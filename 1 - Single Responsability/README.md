# Principio da Responsabilidade Única / Singles Responsability Principle (SRP)

Segunda Marco Túlio Valente, o princípio da responsabilidade única impõe que "toda classe deve ter uma única responsabilidade" e que "deve existir um único motivo para modificar qualquer classe em um sistema". Ou seja, idealmente não devem existir classes que funcionem como canivetes Suíços, muito pelo contrário, elas devem ser especializadas em uma função, o que acarretaria em uma maior coesão e uma maior facilidade de manutenção.

Lucas Diogo em seu Github [LucasDiogo96/S.O.L.I.D](https://github.com/LucasDiogo96/S.O.L.I.D/blob/main/1%20-%20Single%20Responsibility/Problem/Person.cs) traz um código simples que funciona como exemplo pedagógico sobre Single Responsability escrito em C#. É uma classe Person que armazena, valida e persiste dados de uma pessoa, ou seja, tem ao menos três funções que deveriam estar separadas.

Em sua soução, Lucas separa esta classe pessoa e cria a seguinte estrutura:
```
solution/
├── CheckService.cs
├── EventBus.cs
├── EventConecctionFactory.cs
├── IdentificationService.cs
└── Person.cs
```

Agora a nova classe Person.cs detém apenas o seguinte código:
```csharp
using System;

namespace SRP.Solution
{
    public class Person
    {
        public int Id { get; set; }
        public string ITIN { get; set; }
        public string Name { get; set; }
        public DateTime BirthDate { get; set; }

        public bool IsValid()
        {
            return !string.IsNullOrWhiteSpace(Name) &&
                   BirthDate != DateTime.MinValue &&
                  !IdentificationService.ValidateITIN(ITIN);
        }
    }
}
```

Como resultado dessa refatoração, cada classe passa a ser responsável por uma única tarefa, facilitando a manutenção, testes e evolução do sistema. Por exemplo, a validação foi movida para a classe `IdentificationService`, enquanto a persistência de dados pode ser tratada por outra classe especializada. Assim, o código se torna mais modular e alinhado ao princípio da responsabilidade única, reduzindo o acoplamento e aumentando a coesão.

**Principais benefícios do SRP:**
- **Facilidade de manutenção:** Mudanças em uma responsabilidade não afetam outras funcionalidades.
- **Testabilidade:** Classes menores e focadas são mais fáceis de testar.
- **Reutilização:** Componentes especializados podem ser reutilizados em outros contextos.

Adotar o SRP é um passo fundamental para construir sistemas mais robustos, flexíveis e fáceis de evoluir.

---
**Referências:**
- [Marco Túlio Valente - Engenharia de Software Moderna](https://engsoftmoderna.info/cap5.html)
- [Exemplo de código no GitHub](https://github.com/LucasDiogo96/S.O.L.I.D/tree/main/1%20-%20Single%20Responsibility)
