#import "@preview/numbly:0.1.0":numbly

#import "@preview/cuti:0.3.0": show-cn-fakebold

#let cover(
  cover-logo-path: "assets/header.png",
  subject: "",
  title: "",
  college: "",
  major: "",
  class: "",
  author: "",
  student-id: "",
  date: datetime.today(),
) = {
  set align(center)
  show: set text(size: 12pt)

  v(6em)
  
  image("assets/header.png", width: 9.87cm)
  
  v(1em)

  text(
    size: 36pt,
    font: "SimSun",
  )[
    #let tracking = 6pt
    #subject.clusters().join(h(tracking))
  ]

  box(
    height: 3.6cm,
    align(horizon)[
      #text(
        size: 24pt,
        font: "STxihei",
        weight: "bold",
        title,
      )
    ],
  )

  v(1em)

  show: set text(size: 16pt, font: "SimSun")

  let info_key(key) = (
    align(
      right,
      key.clusters().join((4 - key.clusters().len()) * h(1em)) + "：",
    )
  )

  let info_value(body) = {
    body
    v(-0.6em)
    line(length: 100%)
  }
  grid(
    columns: (6em, 14em),
    column-gutter: 1em,
    row-gutter: 0.9em,
    info_key("学院"), info_value(college),
    info_key("专业"), info_value(major),
    info_key("班级"), info_value(class),
    info_key("学生姓名"), info_value(author),
    info_key("学号"), info_value(student-id),
  )
  v(1fr)
  [
    #date.year()#h(0.5em)年#h(0.5em)#date.month()#h(0.5em)月#h(0.5em)#date.day()#h(0.5em)日
  ]
  v(0.6em)

  pagebreak(weak: true)
}

#let report-body(
  class: "",
  student-id: "", 
  author: "",
  header: "",
  title: "",
  body,
) = {

  set text(font: ("Times New Roman", "SimSun"), size: 12pt)
  show par: set par(leading: 14pt, justify: true,first-line-indent: 2em)
  // 这里的leading难道是从8pt开始算起的吗？设置14pt，实际效果是22pt？
  // if counter(page).at().first()  1 {}

  set page(
    header: [
    #align(center)[#header]
    #v(-8pt) // 页眉内容与线的距离
    #line(length: 100%, stroke: (thickness: 1pt, dash: "solid"))
  ],
    numbering: "1"
  )

  set heading(numbering: numbly(
    "",
    "{2:一}、",
    "{2}.{3}",
    "{2}.{3}.{4}",
    "",
  ))
  show heading.where(level: 2): it => {
    set text(font: "SimHei", size: 16pt)
    set block(below: 0em)
    it
  }
  show heading.where(level: 3): it => {
    set text(font: "SimHei", size: 13pt)
    set block(below: 0em)
    it
  }
  show heading.where(level: 4): it => {
    set text(font: "SimHei", size: 12pt)
    set block(below: 0em)
    it
  }
  show heading.where(level: 5): it => {
    set text(font: "SimHei", size: 12pt)
    set block(below: 0em)
    it
  }

  show heading: it =>  {
      it
      par()[#text()[#h(0.0em)]]
  }
  show raw.where(block: false): it => [// 一个换行会产生一个“小空格”（暂时不知道具体是什么），可实现box前后均加一个小空格的效果
    #box(
      fill: luma(230),
      radius: 1pt,
      outset: (x: 3pt, y: 3pt), // 设置outset不会影响行布局，而inset则会使得box内部文字比行内其他文字要高
      text([#it], font: ("Consolas", "KaiTi"), size: 12pt)
  )
  ]
  show raw.where(block: true): it => {
    block(
      fill: luma(230),
      width: 100%,
      radius: 5pt,
      inset: 8pt,
      text(it, font: ("Consolas", "KaiTi"))
    )
    text()[#h(0.0em)] // 用来使得块级元素后分段
    // h(2em) // 也可用这个来实现首行缩进
  }

  show link: it => {
    set text(fill: blue)
    show-cn-fakebold(it)
  }

  set enum(
    numbering: "（1）",
  )

  let title = text(font: "SimHei", size: 18pt)[#title]

  body

}


#let appendix(title) = {
  pagebreak()

  set heading(numbering: numbly(
    "", // use {level:format} to specify the format
    "", // if format is not specified, arabic numbers will be used
    "{3}.", // here, we only want the 3rd level
    "",
  ))
  [== 附录：#title]
}
