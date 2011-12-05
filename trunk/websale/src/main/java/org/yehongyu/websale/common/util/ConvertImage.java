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
 * 【类说明】转换图片类
 * @author yehongyu.org
 * @version 1.0 2007-12-3 下午01:27:04
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
            File fi = new File(fileInput); // 大图文件
            File fo = new File(fileOutput); // 将要转换出的小图文件
            int nw = 150; // 定义宽为150
            int nh = 100; // 定义高为100
            AffineTransform transform = new AffineTransform();
            BufferedImage bis = ImageIO.read(fi);
            int w = bis.getWidth();
            int h = bis.getHeight();

            double sx = (double) nw / w;
            double sy = (double) nh / h;
            
            // 判断是横向图形还是坚向图形
            if (w > h) // 横向图形
            {
                if ((int) (sx * h) > nh) // 比较高不符合高度要求,就按高度比例
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