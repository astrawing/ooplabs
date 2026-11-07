interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int volume);
    int getChannel();
    void setChannel(int channel);

    void printStatus();
}

class TV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() { on = true; }

    @Override
    public void disable() { on = false; }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    @Override
    public int getChannel() { return channel; }

    @Override
    public void setChannel(int channel) { this.channel = channel; }

    @Override
    public void printStatus() {
        System.out.printf("TV | %s | volume=%d | channel=%d%n",
                on ? "ON" : "OFF", volume, channel);
    }
}

class Radio implements Device {
    private boolean on = false;
    private int volume = 20;
    private int channel = 5;

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() { on = true; }

    @Override
    public void disable() { on = false; }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(50, volume)); // у радио максимум 50
    }

    @Override
    public int getChannel() { return channel; }

    @Override
    public void setChannel(int channel) { this.channel = channel; }

    @Override
    public void printStatus() {
        System.out.printf("Radio | %s | volume=%d | channel=%d%n",
                on ? "ON" : "OFF", volume, channel);
    }
}

abstract class Remote {
    protected Device device; // мост к реализации

    public Remote(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void printStatus() {
        device.printStatus();
    }
}

class BasicRemote extends Remote {
    public BasicRemote(Device device) {
        super(device);
    }
}

class AdvancedRemote extends Remote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}

public class RemoteMain {
    public static void main(String[] args) {

        Device tv = new TV();
        Device radio = new Radio();

        Remote basicRemote = new BasicRemote(tv);
        Remote advancedRemote = new AdvancedRemote(radio);

        System.out.println("--- Базовый пульт + TV ---");
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.volumeUp();
        basicRemote.channelUp();
        basicRemote.printStatus();

        System.out.println("\n--- Продвинутый пульт + Radio ---");
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        advancedRemote.printStatus();
        System.out.println(">>> Mute <<<");
        ((AdvancedRemote) advancedRemote).mute();
        advancedRemote.printStatus();
    }
}