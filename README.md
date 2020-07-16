# DES-Simulator

DES-Simulator für die DES-Spielwiese.

Aufgabe 5c)<br>
ParallelisierungProblem: Der Scheduler ist als Singleton implementiert, d.h. bei einer Parallelisierung der Simulation durch die Verwendung mehrererThreads wird er somit zu einer gemeinsamen Ressource.

Das führt zu großen Komplikationen. Zum einen muss für einen wechselseitigen Zugriff auf die PriorityQueue gesorgt werden, damit es nicht zu sogenanntenLost Updates kommen kann(bspw. durch Verwendung einer Semaphore).

Zum anderen müssen die Events auch eine Markierung erhalten, damit die Zuordnung zu der betreffenden Simulation sichergestellt wird (Stichwort: Object Space).

Lösung: Um die Durchführung von Simulationen zu parallelisieren, müssen mehrereThreadsinnerhalb des „newFixedThreadPool“verwendet werden.Jeder dieser Threads bekommt eine eigene Scheduler Instanz als Singleton zugewiesen(Übergabe an den Thread).Somit wird sichergestellt, dass nur ein Thread Zugriff auf einen Scheduler hat. Es kann somit wederzu „LostUpdates“kommen,noch kann die Ausführungsreihenfolge der Events durcheinander gebracht werden.

Aufgabe 6)<br>
Nachdem in constructProcessFlow(Class<?> c) die HashMaps steps und delays erzeugt wurden, werden diese an den ProcessValidator übergeben.l
Der ProcessValidator überprüft jeweils die Methoden in den Maps und wirft ggf. eine ModelException mit aussagekräftiger Fehlermeldung.

Auf negative Delay-Werte kann erst überprüft werden, wenn der nextDelay im ModelProcessExecutor tatsächlich erzeugt wird. Dieser Fehler kann
also nicht vom ProcessValidator überprüft werden und ist deshalb in die getDelay()-Methode ausgelagert.

Die Testfälle in der DES-Spielwiese erwarten alle eine Exception vom Typ ModelException. Da die Exception jedoch bislang nicht vom Simulator entgegen genommen wird kommen die Exception nicht im Test an. Sobald die Exception weitergereicht wird sollten die Testfälle grün sein.
