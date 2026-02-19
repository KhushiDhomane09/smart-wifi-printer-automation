package com.smartprinter.automation.service.printer;

import com.smartprinter.automation.entity.Printer;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.Socket;

@Component
public class Raw9100PrinterClient {

    public void print(Printer printer, byte[] data) throws Exception {
        try (Socket socket = new Socket(printer.getIpAddress(), printer.getPort());
             OutputStream os = socket.getOutputStream()) {
            os.write(data);
            os.flush();
        }
    }
}