import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AssServerRMI extends Remote
{
    public void establish(String x) throws RemoteException;
}
