/*class TestThread implements Runnable {
String name; // ��� ������
Thread t;
boolean suspendFlag;
TestThread(String threadname) {
name = threadname;
t = new Thread(this, name);
System.out.println("����� �����: " + t) ;
suspendFlag = false;
t.start();
// ��������� �����
}
// ����� ����� ������.
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
System.out.println(name + " �������.");
}
System.out.println(name + " ��������.");
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
TestThread obi = new TestThread("����");
TestThread ob2 = new TestThread("���");
try {
Thread.sleep (1000);
obi.mysuspend();
System.out.println("������������ ������ ����");
Thread.sleep(1000);
obi.myresume () ;
System.out.println("������������� ������ ����");
ob2.mysuspend();
System.out.println("������������ ������ ���");
Thread.sleep(1000); ob2.myresume(); 
System.out.println("������������� ������ ���");
}
catch (InterruptedException e) { 
System.out.println("������� ����� �������");
// �������� ���������� ������� try {
System.out.println("�������� ���������� �������.");
obi.t.join ();
ob2.t.join();
}
catch {
(InterruptedException e) {
System.out.println("������� ����� �������");
}
System.out.println("������� ����� ��������");
}
}*/
