import cs2030.util.*
import cs2030.simulator.*
new Server(1)
new Server(2)
new Shop(List.<Server>of(new Server(1), new Server(2)))
Shop shop = new Shop(List.<Server>of(new Server(1), new Server(2)))
new EventStub(new Customer(1, 0.5), 3.5)
new EventStub(new Customer(1, 0.5), 3.5).execute(shop)
new Simulate2(1, List.<Double>of(0.5, 0.6, 0.7))
System.out.println(new Simulate2(1, List.<Double>of(0.5, 0.6, 0.7)).run())
new Simulate2(2, List.<Double>of(3.5, 2.6, 1.7))
System.out.println(new Simulate2(1, List.<Double>of(3.5, 2.6, 1.7)).run())
