# Composição sobre Herança / Composition Over Inheritance

O princípio da composição sobre herança orienta que, sempre que possível, é preferível projetar sistemas utilizando composição em vez de herança de classes. A composição promove maior flexibilidade, menor acoplamento e melhor encapsulamento, o que resulta em sistemas mais robustos e fáceis de manter.

Marco Túlio destaca que existem dois tipos distintos de herança:

- Herança de classes (`class A extends B`), que implica em reúso de código e alto acoplamento entre classes;
- Herança de interfaces (`interface I extends J`), que não promove reúso direto e não é problemática.

Ele ainda destaca que durante a popularização da orientação a objetos, acreditava-se que a herança de classes seria a chave para maximizar o reúso de código. Projetos com hierarquias profundas eram tidos como bem estruturados. Contudo, com o tempo, identificou-se que a herança excessiva introduz forte acoplamento entre superclasses e subclasses, tornando difícil modificar ou estender o sistema sem efeitos colaterais.

Gamma et al. (Design Patterns, 1994) argumentam que a herança “viola o encapsulamento”, pois expõe detalhes internos da superclasse às subclasses. Isso fragiliza a arquitetura: pequenas mudanças em uma classe base podem afetar múltiplas subclasses, quebrando funcionalidades inesperadamente.

A composição, por outro lado, consiste em construir objetos complexos a partir de objetos mais simples. Quando uma classe A possui um atributo do tipo B, diz-se que A compõe B. Essa abordagem favorece a reutilização por delegação em vez de extensão, o que preserva o encapsulamento e permite maior modularidade.

O repositório [ArjanCodes/2021-composition-vs-inheritance](https://github.com/ArjanCodes/2021-composition-vs-inheritance) ilustra bem essa transição. No mau exemplo, utiliza-se herança para representar variações de funcionários:

```python
@dataclass
class SalariedEmployeeWithCommission(SalariedEmployee):
    commission: float = 100
    contracts_landed: float = 0

    def compute_pay(self) -> float:
        return super().compute_pay() + self.commission * self.contracts_landed
```

Essa abordagem gera uma hierarquia inchada e inflexível, onde cada nova combinação de comportamento exige uma nova subclasse. O sistema se torna difícil de estender e manter.

Já no exemplo que aplica o princípio, adota-se composição ao modelar `Contract` e `Commission` como componentes reutilizáveis. A classe `Employee` recebe essas dependências por injeção:

```python
@dataclass
class Employee:
    name: str
    id: int
    contract: Contract
    commission: Optional[Commission] = None

    def compute_pay(self) -> float:
        payout = self.contract.get_payment()
        if self.commission is not None:
            payout += self.commission.get_payment()
        return payout
```

Essa estrutura permite combinar livremente contratos e comissões, sem criar subclasses adicionais. Assim, o comportamento de pagamento é flexível, testável e extensível — um exemplo claro dos benefícios da composição.

Principais vantagens da composição sobre a herança:

- Baixo acoplamento: Evita a dependência rígida entre classes.
- Encapsulamento preservado: Implementações internas permanecem ocultas.
- Extensibilidade: Novas funcionalidades podem ser adicionadas sem alterar estruturas existentes.
- Reutilização modular: Comportamentos podem ser combinados dinamicamente.

Portanto, o princípio não elimina a herança, mas recomenda: quando há duas soluções viáveis, prefira a composição.

---

**Referências:**

* Gamma, E. et al. – [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.javier8a.com/itc/bd1/articulo.pdf)
* Marco Túlio Valente – *Engenharia de Software Moderna* ([Capítulo 5](https://engsoftmoderna.info/cap5.html))
* [Repositório GitHub – ArjanCodes/Composition vs Inheritance](https://github.com/ArjanCodes/2021-composition-vs-inheritance)
