package cn.boood.fireeye.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/1 9:13
 */
public class PublicUtil {
    /**
     * UUID生成
     * @return
     */
    static public String getUUID(){
        return IdUtil.simpleUUID();
    }

    static public String getMD5Hash(String key){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(key);
    }
}
