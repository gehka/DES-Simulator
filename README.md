# DES-Simulator

DES-Simulator für die DES-Spielwiese.

Aufgabe 6)<br>
Nachdem in constructProcessFlow(Class<?> c) die HashMaps steps und delays erzeugt wurden, werden diese an den ProcessValidator übergeben.l
Der ProcessValidator überprüft jeweils die Methoden in den Maps und wirft ggf. eine ModelException mit aussagekräftiger Fehlermeldung.

Auf negative Delay-Werte kann erst überprüft werden, wenn der nextDelay im ModelProcessExecutor tatsächlich erzeugt wird. Dieser Fehler kann
also nicht vom ProcessValidator überprüft werden und ist deshalb in die getDelay()-Methode ausgelagert.

Die Testfälle in der DES-Spielwiese erwarten alle eine Exception vom Typ ModelException. Da die Exception jedoch bislang nicht vom Simulator entgegen genommen wird kommen die Exception nicht im Test an. Sobald die Exception weitergereicht wird sollten die Testfälle grün sein.
