package com.jinxuliang;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

interface FileCopyRunner {
    void copyFile(File source, File target);
}

public class FileCopy {
    private static final int ROUNDS = 5;
    //性能测试
    private static void benchmark(FileCopyRunner test, File source, File target) {
        long elapsed = 0L;
        for (int i = 0; i < ROUNDS; i++) {
            long startTime = System.currentTimeMillis();
            test.copyFile(source, target);
            elapsed += System.currentTimeMillis() - startTime;
            target.delete();
        }
        System.out.println(test + ": " + elapsed / ROUNDS);
    }

    public static void main(String[] args) {

        FileCopyRunner noBufferStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                try (var fin = new FileInputStream(source);
                     var fout = new FileOutputStream(target);) {
                    int result;
                    while ((result = fin.read()) != -1) {
                        fout.write(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return "noBufferStreamCopy";
            }
        };

        FileCopyRunner bufferedStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                try (var fin = new BufferedInputStream(new FileInputStream(source));
                     var fout = new BufferedOutputStream(new FileOutputStream(target));) {
                    byte[] buffer = new byte[1024];
                    int result;
                    while ((result = fin.read(buffer, 0, buffer.length)) != -1) {
                        fout.write(buffer, 0, result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return "bufferedStreamCopy";
            }
        };

        FileCopyRunner nioBufferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                try (
                        var inputStream = new FileInputStream(source);
                        var fin = inputStream.getChannel();
                        var outputStream = new FileOutputStream(target);
                        var fout = outputStream.getChannel();
                ) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (fin.read(buffer) != -1) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            fout.write(buffer);
                        }
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public String toString() {
                return "nioBufferCopy";
            }
        };

        FileCopyRunner nioTransferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                try (
                        var inputStream = new FileInputStream(source);
                        var fin = inputStream.getChannel();
                        var outputStream = new FileOutputStream(target);
                        var fout = outputStream.getChannel();
                ) {
                    long transferred = 0L;
                    long size = fin.size();
                    while (transferred != size) {
                        transferred += fin.transferTo(0, size, fout);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public String toString() {
                return "nioTransferCopy";
            }
        };

        //大约几百K
        File smallFile = new File("d:/test.pptx");
        File smallFileCopy = new File("d:/test_copy.pptx");
        System.out.println("---Copying small file---");
        benchmark(noBufferStreamCopy, smallFile, smallFileCopy);
        benchmark(bufferedStreamCopy, smallFile, smallFileCopy);
        benchmark(nioBufferCopy, smallFile, smallFileCopy);
        benchmark(nioTransferCopy, smallFile, smallFileCopy);

        //大约几百M
        File bigFile = new File("D:/test.mp4");
        File bigFileCopy = new File("D:/test_copy.mp4");
        System.out.println("---Copying big file---");
        //benchmark(noBufferStreamCopy, bigFile, bigFileCopy);
        benchmark(bufferedStreamCopy, bigFile, bigFileCopy);
        benchmark(nioBufferCopy, bigFile, bigFileCopy);
        benchmark(nioTransferCopy, bigFile, bigFileCopy);
    }
}
