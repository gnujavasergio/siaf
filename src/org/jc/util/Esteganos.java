package org.jc.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Esteganos {

    private String firma = "jc";
    private int Longitud = 0;
    private BufferedImage foto = null;
    private int r;
    private int g;
    private int b;
    private Color color;
    private String mensaje_binario;
    private String mensaje_original;
    private int contador = 0;

    private void SetMensaje(String mensaje) {
        String bi = "";

        this.Longitud = (mensaje.length() + this.firma.length() * 2);

        for (int i = 15; i >= 0; i--) {
            bi = bi + ((this.Longitud & 1 << i) > 0 ? "1" : "0");
        }

        this.mensaje_binario = (getMensajeToBinary(this.firma) + bi + getMensajeToBinary(mensaje));
    }

    public void OcultarMensaje(BufferedImage f, String mensaje) {
        int tmp_count = 0;

        SetMensaje(mensaje);

        this.foto = new BufferedImage(f.getWidth(), f.getHeight(), 1);

        for (int fila = 0; fila < this.foto.getHeight(); fila++) {
            for (int columna = 0; columna < this.foto.getWidth(); columna++) {
                this.color = new Color(f.getRGB(columna, fila));

                if (tmp_count <= this.mensaje_binario.length()) {
                    String red = toBinary((byte) this.color.getRed());
                    String verde = toBinary((byte) this.color.getGreen());
                    String azul = toBinary((byte) this.color.getBlue());

                    red = ReemplazarLSB(red);
                    verde = ReemplazarLSB(verde);
                    azul = ReemplazarLSB(azul);

                    this.r = Integer.parseInt(red, 2);
                    this.g = Integer.parseInt(verde, 2);
                    this.b = Integer.parseInt(azul, 2);
                } else {
                    this.r = this.color.getRed();
                    this.g = this.color.getGreen();
                    this.b = this.color.getBlue();
                }

                this.foto.setRGB(columna, fila, new Color(this.r, this.g, this.b).getRGB());
                tmp_count += 3;
            }
        }
    }

    private boolean leerfirma(BufferedImage f) {
        boolean ok = false;
        String t = "";
        for (int j = 0; j < 6; j++) {
            this.color = new Color(f.getRGB(j, 0));
            String red = toBinary((byte) this.color.getRed());
            String verde = toBinary((byte) this.color.getGreen());
            String azul = toBinary((byte) this.color.getBlue());
            red = getLSB(red);
            verde = getLSB(verde);
            azul = getLSB(azul);
            t = t + red + verde + azul;
        }
        if ((toChar(t.substring(0, 8)).equals("j")) && (toChar(t.substring(8, 16)).equals("c"))) {
            ok = true;
        }
        return ok;
    }

    private void LeerLongitudDelMensaje(BufferedImage f) {
        String t = "";
        for (int j = 5; j < 12; j++) {
            this.color = new Color(f.getRGB(j, 0));
            String red = toBinary((byte) this.color.getRed());
            String verde = toBinary((byte) this.color.getGreen());
            String azul = toBinary((byte) this.color.getBlue());
            red = getLSB(red);
            verde = getLSB(verde);
            azul = getLSB(azul);
            t = t + red + verde + azul;
        }
        this.Longitud = toCharInt(t.substring(1, 17));
    }

    public String getMensajeToImage(BufferedImage f) {
        this.mensaje_original = "No existe ningún mensaje oculto";
        if (leerfirma(f)) {
            LeerLongitudDelMensaje(f);

            String[] s = new String[this.Longitud];
            String tmp = "";

            for (int fila = 0; fila < f.getHeight(); fila++) {
                for (int columna = 0; columna < f.getWidth(); columna++) {
                    this.color = new Color(f.getRGB(columna, fila));

                    String red = toBinary((byte) this.color.getRed());
                    String verde = toBinary((byte) this.color.getGreen());
                    String azul = toBinary((byte) this.color.getBlue());

                    red = getLSB(red);
                    verde = getLSB(verde);
                    azul = getLSB(azul);

                    if (tmp.length() > this.Longitud * 8) {
                        break;
                    }
                    tmp = tmp + red + verde + azul;
                }

            }

            int count_tmp = 0;
            for (int a = 0; a < this.Longitud * 8; a += 8) {
                s[count_tmp] = tmp.substring(a, a + 8);
                count_tmp++;
            }

            this.mensaje_original = getMensajeToString(s);
        }
        return this.mensaje_original;
    }

    public BufferedImage getFoto() {
        return this.foto;
    }

    private String toBinary(byte caracter) {
        byte byteDeCaracter = caracter;
        String binario = "";
        for (int i = 7; i >= 0; i--) {
            binario = binario + ((byteDeCaracter & 1 << i) > 0 ? "1" : "0");
        }
        return binario;
    }

    private String toChar(String binario) {
        int i = Integer.parseInt(binario, 2);
        String aChar = new Character((char) i).toString();
        return aChar;
    }

    private int toCharInt(String binario) {
        int i = Integer.parseInt(binario, 2);
        return i;
    }

    private String getMensajeToBinary(String mensaje) {
        String mb = "";
        char[] mensaje_tmp = mensaje.toCharArray();
        for (int i = 0; i < mensaje_tmp.length; i++) {
            mb = mb + toBinary((byte) mensaje_tmp[i]);
        }
        return mb;
    }

    private String getMensajeToString(String[] mensaje) {
        String mo = "";

        for (int i = 4; i < mensaje.length; i++) {
            mo = mo + toChar(mensaje[i]);
        }
        return mo;
    }

    private String ReemplazarLSB(String colorRGB) {
        if (this.contador < this.mensaje_binario.length()) {
            colorRGB = colorRGB.substring(0, 7) + this.mensaje_binario.substring(this.contador, this.contador + 1);
            this.contador += 1;
        }
        return colorRGB;
    }

    private String getLSB(String binario) {
        return binario.substring(7, 8);
    }
}