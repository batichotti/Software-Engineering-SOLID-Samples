# Princípio Aberto-Fechado / Open-Closed Principle (OCP)

Segundo Bertrand Meyer, formulador do princípio, o Open-Closed Principle fala que “softwares devem ser abertos para extensão, mas fechados para modificação”. Isso significa que, ao invés de alterar classes já existentes (e potencialmente estáveis), deve-se estender seu comportamento por meio de herança, interfaces ou composição. O principal objetivo é evitar efeitos colaterais indesejados ao modificar códigos consolidados, promovendo sistemas mais estáveis, reutilizáveis e resilientes a mudanças.

Segundo Marco Túlio Valente, em Engenharia de Software Moderna, "o OCP é um dos pilares para a construção de sistemas que evoluem sem comprometer sua integridade". Ele destaca que, ao adotar interfaces e abstrações bem definidas, como no exemplo refatorado, o código passa a depender de contratos estáveis, não de implementações concretas. Isso reduz o acoplamento e permite que novas funcionalidades sejam incorporadas sem riscos de propagar erros em módulos já testados e validados. Valente reforça que o princípio não se limita a herança, mas engloba estratégias como composição e injeção de dependência, garantindo flexibilidade para adaptar o sistema a requisitos futuros de forma previsível e segura. Essa abordagem, segundo ele, é fundamental em projetos de médio e grande porte, onde a estabilidade do núcleo do sistema é tão crítica quanto a capacidade de evoluir rapidamente.

Um exemplo que pode ser encontrado no GitHub está no repositório [mrbrunelli/open-closed-principle](https://github.com/mrbrunelli/open-closed-principle), escrito em TypeScript. Inicialmente, o código apresenta a seguinte estrutura com violação do OCP:

```typescript
class Senior {
    salary (): number {
        return 7000
    }
}

class Trainee {
    subsidy (): number {
        return 600
    }
}

class Payroll {
    protected balance: number = 0

    calculate (employe: any) {
        if (employe instanceof Senior) {
            this.balance = employe.salary()
        } else if (employe instanceof Trainee) {
            this.balance = employe.subsidy()
        }
        return this.balance
    }
}

const senior = new Senior()
const trainee = new Trainee()
const payroll = new Payroll()

console.log('Senior: ' + payroll.calculate(senior))
console.log('Trainee: ' + payroll.calculate(trainee))
```

Nesse exemplo, sempre que um novo tipo de funcionário é adicionado, é necessário alterar a classe `Payroll`, o que a torna fechada para extensão e aberta para modificação, violando diretamente o OCP.

A refatoração proposta reorganiza o código da seguinte forma:

```typescript
interface Remunerable {
  calculatePay(): number;
}

class Intern implements Remunerable {
  constructor(private hours: number) {}
  calculatePay(): number {
    return this.hours * 10;
  }
}

class Junior implements Remunerable {
  constructor(private hours: number) {}
  calculatePay(): number {
    return this.hours * 20;
  }
}

class Payroll {
  calculate(employee: Remunerable): number {
    return employee.calculatePay();
  }
}
```

Com essa mudança, novas regras de cálculo de salário podem ser introduzidas apenas com a criação de novas classes que implementam a interface `Remunerable`. A classe `Payroll` permanece inalterada, agora fechada para modificação, mas aberta para extensão.

Principais benefícios do OCP:
- Segurança em alterações: Evita a modificação de código existente, reduzindo a chance de regressões.
- Extensibilidade: Facilita a adição de novas funcionalidades com mínimo impacto.
- Organização e modularidade: Incentiva o uso de abstrações, promovendo um design limpo e flexível.

Adotar o OCP é essencial para manter a estabilidade de sistemas em evolução constante, permitindo que cresçam de forma sustentável e com menor risco técnico.

---

**Referências:**
- Bertrand Meyer. [Object-Oriented Software Construction](https://bertrandmeyer.com/wp-content/upLoads/OOSC2.pdf) (1988)
- [Exemplo de código no GitHub](https://github.com/mrbrunelli/open-closed-principle)  
- [Marco Túlio Valente - Engenharia de Software Moderna](https://engsoftmoderna.info/cap5.html)  
