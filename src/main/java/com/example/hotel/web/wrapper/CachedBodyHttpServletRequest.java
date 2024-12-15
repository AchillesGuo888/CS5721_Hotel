package com.example.hotel.web.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
  private final byte[] cachedBody;

  public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
    super(request);
    InputStream requestInputStream = request.getInputStream();
    this.cachedBody = readAllBytes(request.getInputStream());
  }
  public static byte[] readAllBytes(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] temp = new byte[1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(temp)) != -1) {
      buffer.write(temp, 0, bytesRead);
    }
    return buffer.toByteArray();
  }

  @Override
  public ServletInputStream getInputStream() {
    return new CachedBodyServletInputStream(this.cachedBody);
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }

  private static class CachedBodyServletInputStream extends ServletInputStream {
    private final ByteArrayInputStream buffer;

    public CachedBodyServletInputStream(byte[] cachedBody) {
      this.buffer = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public boolean isFinished() {
      return buffer.available() == 0;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
      // No-op
    }

    @Override
    public int read() {
      return buffer.read();
    }
  }
}
