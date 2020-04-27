package com.example.distributed.crawling.conf;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileBased;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类是master或者slave的配置配
 */
public class MasterOrSlaveConfigure implements FileBased {
    private static final Logger log = LoggerFactory.getLogger(MasterOrSlaveConfigure.class);
    private List<String> contentList;
    public MasterOrSlaveConfigure(){
        contentList = new ArrayList<>();
    }
    @Override
    public void read(Reader in) throws ConfigurationException, IOException {
        BufferedReader bufferedReader = new BufferedReader(in);
        String lineContent = null;
        while((lineContent = bufferedReader.readLine()) != null){
            contentList.add(lineContent);
        }
        bufferedReader.close();
    }

    public List<String> getContentList() {
        return contentList;
    }

    @Override
    public void write(Writer out) throws ConfigurationException, IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        if(contentList.isEmpty()){
            log.info("contentList is empty, so don not had to write to this file");
        }else{
            for(String content: contentList){
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            log.info("contentList's content is write to the file");
        }
        bufferedWriter.close();
    }
}
