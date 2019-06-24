# 티켓 판매 애플리케이션 구현하기

## 최초 설계

### **초대장 객체 구현**

```java
public class Invitaion {
    private LocalDateTime when;
}
```

### **티켓 객체 구현**

공연을 관람하기 원하는 모든 사람들은 티켓을 소지하고 있어야만 한다.

```java
public class Ticket {
    private Long fee;

    public Long getFee() {
        return fee;
    }
}
```

### **가방 객체 구현**

소지품을 보관할 가방 객체 구현

```java
public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;        
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
```

### **가방 인스턴스 생성자 추가**

이벤트에 당첨된 관람객의 가방 안에는 현금과 초대장이 들어있지만 이벤트에 당첨되지 않은 관람객의 가방 안에는 초대장이 들어있지 않을 것이다. 따라서 Bag 인스턴스의 상태는 현금과 초대장을 함께 보관하거나, 초대장 없이 현금만 보관하는 두 가지 중 하나일 것이다. Bag의 인스턴스를 새성하는 시점에 이 제약을 강제할 수 있도록 생성자를 추가하자.

```java
public class Bag {
    public Bag(Long amount) {
        this(null, amount);
    }

    public Bag(Invitation invitation, Long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }
}
```

### **관람객 객체 구현**

관람객은 소지품을 보관하기 위해 가방을 소지할 수 있다.

```java
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }
}
```

### **매표소 객체 구현**

매표소에는 관람객에게 판매할 티켓과 티켓의 판매 금액이 보관돼 있어야 한다.

```java
public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
```

### **판매원 객체 구현**

판매원은 매표소에서 초대장을 티켓으로 교환해 주거나 티켓을 판매하는 역할을 수행한다. 판매원을 구현한 TicketSeller 클래스는 자신이 일하는 매표소(ticketOffice)를 알고 있어야 한다.

```java
public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }
}
```

### **소극장 객체 구현**

모든 준비가 끝났다. 이제 지금까지 준비한 그림 1.1의 클래스들을 조합해서 관람객을 소극장에 입장시키는 로직을 완성하는 일만 남았다.
```java
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            System.out.println("초대권이 있다.");
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            System.out.println("초대권이 없다.");
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
            
        }
    }
}
```