O objetivo desta atividade foi investigar o comportamento da execução de um código de verificar números primos de uma lista de forma sequencial comparada a de forma paralela utilizando uma, cinco e dez threads.

Além disso, implementamos o uso da palavra reservada "synchronized" do Java para controlar o acesso à lista de números, garantindo que as threads alternem o acesso à região crítica da lista, onde os números foram armazenados.

Na abordagem sequencial, onde apenas uma thread é utilizada, a complexidade de tempo é linear (O(n)), pois cada elemento da lista é processado em sequência, sem paralelismo.

Na abordagem paralela, a complexidade de tempo pode ser reduzida para O(n/p), onde n é o tamanho da lista e p é o número de threads. No entanto, é importante destacar que essa é uma simplificação e outros fatores, como sobrecarga de comunicação e sincronização entre threads, podem influenciar o desempenho. Portanto, embora a execução paralela possa oferecer uma potencial redução no tempo total de execução, essa redução pode não ser linearmente proporcional ao número de threads utilizadas.

Assim, uma análise adequada requer a consideração dos tempos de execução observados em cada configuração (uma, cinco e dez threads) e a comparação desses resultados para avaliar de forma precisa o impacto do paralelismo no desempenho do sistema conforme demonstra o gráfico:

![image](https://github.com/enzogebauer/java-threads-check-prime-numbers/assets/80331520/990942f9-9c1d-4692-8874-7fbf80f1a514)

Nele pode-se observar a diminuição linear no tempo de execução ao aumentar o número de threads de 1 para 5, o que era esperada, pois a capacidade de processamento é distribuída entre várias threads, permitindo que mais trabalho seja feito simultaneamente. No entanto, ao aumentar o número de threads de 5 para 10, a diminuição no tempo de execução pode não ser tão grande devido a alguns fatores:

Overhead de Threads: Cada thread tem um certo overhead associado à sua criação, gerenciamento e sincronização. Quando o número de threads aumenta, o overhead também aumenta, podendo diminuir a eficiência geral do programa.

Limitações de Hardware: O hardware, como o número de núcleos de CPU e a capacidade de memória, também pode limitar o benefício de adicionar mais threads. Se o hardware não puder suportar eficientemente um grande número de threads, o aumento do número de threads pode levar a uma degradação do desempenho devido à competição por recursos.

Conclusão:

Para conjuntos de dados menores ou problemas menos intensivos em processamento, a implementação sequencial pode ser adequada devido à sua simplicidade e eficiência.
Para conjuntos de dados maiores ou problemas que podem ser paralelizados eficientemente, as implementações paralelas oferecem uma significativa redução no tempo de execução.
A escolha do número ideal de threads depende de vários fatores, incluindo o tamanho do problema, a arquitetura do sistema e a capacidade de hardware. Experimentação e análise são essenciais para determinar a configuração mais eficaz.





