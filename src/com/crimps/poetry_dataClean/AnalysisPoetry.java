/**
 * @(#)com.crimps.poetry_dataClean.AnalysisPoetry.java Copyright (c) 2014-2018 crimps
 */
package com.crimps.poetry_dataClean;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crimps
 * @version 1.0  17/3/3
 * @modified crimps  17/3/3  <创建>
 */
public class AnalysisPoetry {

    private PoetryDataSource poetryDataSource = new PoetryDataSource();

    /**
     * 句子入库
     */
    public void saveSentence() throws Exception{
        String sql = " select * from tb_poetry ";
        List<Map<String, String>> resultMapList = poetryDataSource.getMapBySimpleSql(sql);
        for (Map<String, String> map : resultMapList) {
            List<TbSentence> tbSentenceList = new ArrayList<>();
            String content = map.get(PoetryDataSource.TABLE_CONTENT).toString().replace("。", "，");
            List<String> sentenceList = Arrays.asList(content.split("，"));
            for (int i = 0; i < sentenceList.size(); i++) {
                TbSentence tbSentence = new TbSentence();
                tbSentence.setId(map.get(PoetryDataSource.TABLE_ROLLID).toString() +
                    map.get(PoetryDataSource.TABLE_ARTICLESID).toString() + formateIndex(i + 1));
                tbSentence.setRollId(map.get(PoetryDataSource.TABLE_ROLLID));
                tbSentence.setArticlesId(map.get(PoetryDataSource.TABLE_ARTICLESID));
                tbSentence.setBeforId(getBeforId(i, map.get(PoetryDataSource.TABLE_ROLLID) +
                                                        map.get(PoetryDataSource.TABLE_ARTICLESID)));
                tbSentence.setAfterId(getAferId(i, sentenceList.size(), map.get(PoetryDataSource.TABLE_ROLLID) +
                                                                                map.get(PoetryDataSource.TABLE_ARTICLESID)));
                tbSentence.setPoertyId(map.get(PoetryDataSource.TABLE_ID));
                tbSentence.setContent(sentenceList.get(i));
                tbSentenceList.add(tbSentence);
            }
            for (TbSentence tbSentence : tbSentenceList) {
                try {
                    poetryDataSource.saveBySimpleSql(createInsertSql(tbSentence));
                } catch (Exception e) {
                    System.out.println(tbSentence.getPoertyId());
                }

            }
        }
    }

    private String createInsertSql(TbSentence tbSentence) {
        return "INSERT INTO tb_sentence VALUES(" + tbSentence.getId() + "," + tbSentence.getRollId() + "," +
            tbSentence.getArticlesId() + "," + tbSentence.getBeforId() + "," + tbSentence.getAfterId() + "," +
            tbSentence.getPoertyId() + ",'" + tbSentence.getContent() + "')";
    }

    private String formateIndex(int index) {
        if (index < 10) {
            return "0" + index;
        } else {
            return String.valueOf(index);
        }
    }

    private String getBeforId(int index, String rex) {
        if (index > 0) {
            return rex + formateIndex(index - 1);
        } else {
            return null;
        }
    }

    private String getAferId(int index, int length, String rex) {
        if (index < length) {
            return rex + formateIndex(index + 2);
        } else {
            return null;
        }
    }
}