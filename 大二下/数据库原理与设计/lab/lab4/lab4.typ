#import "template-dblab.typ": report-body, appendix, sqlrequest
#import "@preview/cetz:0.3.4": canvas, draw, tree

#show: doc => report-body(
  class: "07112304",
  student-id: "1120233329",
  author: "陈墨霏",
  header: "数据库原理与设计实验报告",
  title: "实验4 数据库备份、恢复和权限管理",
  doc
)
#set par(
  leading: 14pt, // enum默认使用par的leading作为每一项的间距（默认下tight=true）
  // 这里其实我不是很能理解，为什么这里的设置不会影响除了enum之外的其他内容
)