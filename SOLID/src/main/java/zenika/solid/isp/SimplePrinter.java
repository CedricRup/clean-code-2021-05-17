package zenika.solid.isp;

public class SimplePrinter implements ICanPrint {

	@Override
	public void print() {
		System.out.println("Print pages");
	}

}
