O objetivo desta atividade foi investigar o comportamento da execução de um código de forma sequencial comparada a de forma paralela utilizando uma, cinco e dez threads.

Além disso, implementamos o uso da palavra reservada "synchronized" do Java para controlar o acesso à lista de números, garantindo que as threads alternem o acesso à região crítica da lista, onde os números foram armazenados.

Na abordagem sequencial, onde apenas uma thread é utilizada, a complexidade de tempo é linear (O(n)), pois cada elemento da lista é processado em sequência, sem paralelismo.

Na abordagem paralela, a complexidade de tempo pode ser reduzida para O(n/p), onde n é o tamanho da lista e p é o número de threads. No entanto, é importante destacar que essa é uma simplificação e outros fatores, como sobrecarga de comunicação e sincronização entre threads, podem influenciar o desempenho. Portanto, embora a execução paralela possa oferecer uma potencial redução no tempo total de execução, essa redução pode não ser linearmente proporcional ao número de threads utilizadas.

Assim, uma análise adequada requer a consideração dos tempos de execução observados em cada configuração (uma, cinco e dez threads) e a comparação desses resultados para avaliar de forma precisa o impacto do paralelismo no desempenho do sistema conforme demonstra o gráfico:

![image](https://github.com/enzogebauer/java-threads-check-prime-numbers/assets/80331520/990942f9-9c1d-4692-8874-7fbf80f1a514)

Nele podemos ver que quando comparamos uma única thread com 5 temos uma diminuiçāo linear como esperado, entretando, essa diminuiçāo nāo se manteve ao aumentar-se o número de threads de 5 para 10.



