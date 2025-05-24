# Lei de Demeter / Law of Demeter

A Lei de Demeter, também conhecida como Princípio do Menor Conhecimento (Principle of Least Knowledge), foi formulada no final da década de 1980 por um grupo de pesquisa da Northeastern University, em Boston, conhecido como **Demeter Project. O objetivo era melhorar a modularização e a manutenibilidade de sistemas orientados a objetos.

Segundo Marco Túlio Valente, este princípio determina que a implementação de um método deve interagir apenas com objetos que estejam "próximos" a ele. Mais precisamente, um método pode invocar métodos apenas de:

1. Sua própria classe
2. Objetos passados como parâmetro
3. Objetos que ele mesmo criou
4. Atributos da própria classe

A ideia central é minimizar o acoplamento e restringir o conhecimento que uma classe tem sobre as estruturas internas de outras. Ao violar esse princípio, cria-se um efeito conhecido como "train wreck", em que chamadas encadeadas expõem o encadeamento de dependências internas de um objeto, fragilizando o sistema.

Exemplo que viola a Lei de Demeter:

```java
var deliveryDate =
    order.getShipment()
         .getDeliveryDetail()
         .getDeliveryDate();
```

Neste caso, `main()` conhece não apenas `order`, mas também `shipment`, `deliveryDetail` e seus respectivos métodos. Essa cadeia de chamadas fere o encapsulamento e torna o código mais frágil a mudanças internas nas classes.

Exemplo que respeita a Lei de Demeter:

```java
var deliveryDate = order.getDeliveryDate();
```

A lógica de navegação entre objetos é encapsulada dentro de `order`, mantendo o método `main()` com conhecimento restrito e focado apenas no que realmente precisa: a data de entrega.

Vantagens da aplicação do princípio:
- Baixo acoplamento entre classes.
- Encapsulamento reforçado, ocultando detalhes internos.
- Maior facilidade de manutenção, pois mudanças internas não afetam consumidores.
- Legibilidade e simplicidade no código de alto nível.

Aplicar a Lei de Demeter leva a um projeto mais modular e resiliente. Embora exija criar métodos de delegação adicionais, o custo é compensado pela melhoria na manutenibilidade e clareza do código.

---

**Referências:**

* Marco Túlio Valente – *Engenharia de Software Moderna* ([Capítulo 5](https://engsoftmoderna.info/cap5.html))
* [Repositório GitHub – buildrun-tech/buildrun-law-of-demeter](https://github.com/buildrun-tech/buildrun-law-of-demeter)
* Lieberherr, K. et al. – [Adaptive Object-Oriented Software The Demeter Method](https://pubs.dbs.uni-leipzig.de/se/files/Lieberherr1996AdaptiveObjectOriented.pdf)
