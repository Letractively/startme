/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * ����˵����ת��ͼƬ��
 * @author yehongyu.org
 * @version 1.0 2007-12-3 ����01:27:04
 */
public class ConvertImage {

    private String fileInput;

    private String fileOutput;

    public ConvertImage() {}

    public String getFileInput() {
        return fileInput;
    }

    public void setFileInput(String fileInput) {
        this.fileInput = fileInput;
    }

    public String getFileOutput() {
        return fileOutput;
    }

    public void setFileOutput(String fileOutput) {
        this.fileOutput = fileOutput;
    }

    public void convert() {
        try {
            File fi = new File(fileInput); // ��ͼ�ļ�
            File fo = new File(fileOutput); // ��Ҫת������Сͼ�ļ�
            int nw = 150; // �����Ϊ150
            int nh = 100; // �����Ϊ100
            AffineTransform transform = new AffineTransform();
            BufferedImage bis = ImageIO.read(fi);
            int w = bis.getWidth();
            int h = bis.getHeight();

            double sx = (double) nw / w;
            double sy = (double) nh / h;
            
            // �ж��Ǻ���ͼ�λ��Ǽ���ͼ��
            if (w > h) // ����ͼ��
            {
                if ((int) (sx * h) > nh) // �Ƚϸ߲����ϸ߶�Ҫ��,�Ͱ��߶ȱ���
                {
                    sx = sy;
                    nw = (int) (w * sx);
                } else {
                    sy = sx;
                    nh = (int) (h * sy);
                }
            } else {
                if ((int) (sy * w) > nw) {
                    sy = sx;
                    nh = (int) (h * sy);
                } else {
                    sx = sy;
                    nw = (int) (w * sx);
                }
            }
            
            transform.setToScale(sx, sy);
            AffineTransformOp ato = new AffineTransformOp(transform, null);
            BufferedImage bid = new BufferedImage(nw, nh,
                    BufferedImage.TYPE_3BYTE_BGR);
            ato.filter(bis, bid);
            ImageIO.write(bid, "jpeg", fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}