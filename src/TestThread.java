/*class TestThread implements Runnable {
String name; // имя потока
Thread t;
boolean suspendFlag;
TestThread(String threadname) {
name = threadname;
t = new Thread(this, name);
System.out.println("Новый поток: " + t) ;
suspendFlag = false;
t.start();
// запустить поток
}
// Точка входа потока.
public void run() {
try {
for(int i = 15; i > 0; i--) {
System.out.println(name + ": " + i);
Thread.sleep(200);
synchronized(this) {
while(suspendFlag) {
wait();
}
}
}
}
catch (InterruptedException e) {
System.out.println(name + " прерван.");
}
System.out.println(name + " завершен.");
}
void mysuspend() {
suspendFlag = true;
}
synchronized void myresume(){
suspendFlag = false;
notify();
}
}
class SuspendResume {
public static void main(String args[]) {
TestThread obi = new TestThread("Один");
TestThread ob2 = new TestThread("Два");
try {
Thread.sleep (1000);
obi.mysuspend();
System.out.println("Приостановка потока Один");
Thread.sleep(1000);
obi.myresume () ;
System.out.println("Возобновление потока Один");
ob2.mysuspend();
System.out.println("Приостановка потока Два");
Thread.sleep(1000); ob2.myresume(); 
System.out.println("Возобновление потока Два");
}
catch (InterruptedException e) { 
System.out.println("Главный поток прерван");
// ожидание завершения потоков try {
System.out.println("Ожидание завершения потоков.");
obi.t.join ();
ob2.t.join();
}
catch {
(InterruptedException e) {
System.out.println("Главный поток прерван");
}
System.out.println("Главный поток завершен");
}
}*/
