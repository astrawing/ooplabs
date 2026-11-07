interface TypeCPort {
    void connectTypeC();
    void transmitAudio(String sound);
}

class Jack35Headphones {
    public void plugJack35() {
        System.out.println("Наушники подключены через Jack 3.5 mm");
    }

    public void playSound(String sound) {
        System.out.println("Наушники воспроизводят: " + sound);
    }
}

class JackToTypeCAdapter implements TypeCPort {
    private final Jack35Headphones headphones;

    public JackToTypeCAdapter(Jack35Headphones headphones) {
        this.headphones = headphones;
    }

    @Override
    public void connectTypeC() {
        System.out.println("Адаптер вставлен в порт Type-C смартфона");
        headphones.plugJack35();
    }

    @Override
    public void transmitAudio(String sound) {
        headphones.playSound(sound);
    }
}

public class MainHeadphones {
    public static void main(String[] args) {
        Jack35Headphones oldHeadphones = new Jack35Headphones();

        TypeCPort smartphonePort = new JackToTypeCAdapter(oldHeadphones);

        smartphonePort.connectTypeC();
        smartphonePort.transmitAudio("Imagine Dragons - Believer");
    }
}