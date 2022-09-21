package com.updev.board;

public class Board {

   int b_num;
   String b_cate,b_kind,b_title,m_nick,b_wdate,b_content;
   int b_likecnt,b_readcnt,b_group,b_step,b_indent;
   String b_tag,b_file1,b_file2;
   int b_report;
   
   public Board() {}

   public Board(int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_wdate,
         String b_content, int b_likecnt, int b_readcnt, int b_group, int b_step, int b_indent, String b_tag,
         String b_file1, String b_file2, int b_report) {
      super();
      this.b_num = b_num;
      this.b_cate = b_cate;
      this.b_kind = b_kind;
      this.b_title = b_title;
      this.m_nick = m_nick;
      this.b_wdate = b_wdate;
      this.b_content = b_content;
      this.b_likecnt = b_likecnt;
      this.b_readcnt = b_readcnt;
      this.b_group = b_group;
      this.b_step = b_step;
      this.b_indent = b_indent;
      this.b_tag = b_tag;
      this.b_file1 = b_file1;
      this.b_file2 = b_file2;
      this.b_report = b_report;
   }

   public int getB_num() {
      return b_num;
   }

   public void setB_num(int b_num) {
      this.b_num = b_num;
   }

   public String getB_cate() {
      return b_cate;
   }

   public void setB_cate(String b_cate) {
      this.b_cate = b_cate;
   }

   public String getB_kind() {
      return b_kind;
   }

   public void setB_kind(String b_kind) {
      this.b_kind = b_kind;
   }

   public String getB_title() {
      return b_title;
   }

   public void setB_title(String b_title) {
      this.b_title = b_title;
   }

   public String getM_nick() {
      return m_nick;
   }

   public void setM_nick(String m_nick) {
      this.m_nick = m_nick;
   }

   public String getB_wdate() {
      return b_wdate;
   }

   public void setB_wdate(String b_wdate) {
      this.b_wdate = b_wdate;
   }

   public String getB_content() {
      return b_content;
   }

   public void setB_content(String b_content) {
      this.b_content = b_content;
   }

   public int getB_likecnt() {
      return b_likecnt;
   }

   public void setB_likecnt(int b_likecnt) {
      this.b_likecnt = b_likecnt;
   }

   public int getB_readcnt() {
      return b_readcnt;
   }

   public void setB_readcnt(int b_readcnt) {
      this.b_readcnt = b_readcnt;
   }

   public int getB_group() {
      return b_group;
   }

   public void setB_group(int b_group) {
      this.b_group = b_group;
   }

   public int getB_step() {
      return b_step;
   }

   public void setB_step(int b_step) {
      this.b_step = b_step;
   }

   public int getB_indent() {
      return b_indent;
   }

   public void setB_indent(int b_indent) {
      this.b_indent = b_indent;
   }

   public String getB_tag() {
      return b_tag;
   }

   public void setB_tag(String b_tag) {
      this.b_tag = b_tag;
   }

   public String getB_file1() {
      return b_file1;
   }

   public void setB_file1(String b_file1) {
      this.b_file1 = b_file1;
   }

   public String getB_file2() {
      return b_file2;
   }

   public void setB_file2(String b_file2) {
      this.b_file2 = b_file2;
   }

   public int getB_report() {
      return b_report;
   }

   public void setB_report(int b_report) {
      this.b_report = b_report;
   }
   
   
}