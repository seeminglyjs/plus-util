package com.source.plusutil.utils.protect;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    //request중 body에 있는 값은 한 번 읽으면 날라가기 떄문에 wrapper로 감싸주어야 한다.

    private byte[] rawData;
    private final HttpServletRequest request;
    private final ResettableServletInputStream servletStream;

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
        this.servletStream = new ResettableServletInputStream();
    }

    public void resetInputStream(byte[] newRawData) {
        servletStream.stream = new ByteArrayInputStream(newRawData);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (rawData == null) {
            rawData = bufferedReaderToByteArray(this.request.getReader());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return servletStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (rawData == null) {
            rawData = bufferedReaderToByteArray(this.request.getReader());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return new BufferedReader(new InputStreamReader(servletStream));
    }

    private static class ResettableServletInputStream extends ServletInputStream {

        private InputStream stream;

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }
    }

    /*
     * BufferedReader 를 Byte[] 배열 형태로 바꿔서 리턴한다.
     *
     * @param bufferedReader
     * @return byte[]
     * @throws IOException
     */
    public byte[] bufferedReaderToByteArray(BufferedReader bufferedReader) throws IOException {
        if(bufferedReader == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString().getBytes();
    }

}