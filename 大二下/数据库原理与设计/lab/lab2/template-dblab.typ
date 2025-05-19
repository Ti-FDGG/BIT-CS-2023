#import "@preview/numbly:0.1.0":numbly

#import "@preview/cuti:0.3.0": show-cn-fakebold

#let appendix() = {
  pagebreak()

  set heading(numbering: numbly(
    "", // use {level:format} to specify the format
    "", // if format is not specified, arabic numbers will be used
    "{3}.", // here, we only want the 3rd level
    "",
  ))
  [== 附录：程序清单及说明]
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
    set text(font: "SimHei", size: 14pt)
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

  show raw.where(block: false): it => box(
      fill: luma(230),
      radius: 1pt,
      outset: (y: 3pt), // 设置outset不会影响行布局，而inset则会使得box内部文字比行内其他文字要高
      text(it, font: ("Consolas", "KaiTi"), size: 12pt)
  )
  show raw.where(block: true): it => {
    block(
      fill: luma(230),
      width: 100%,
      radius: 5pt,
      inset: 8pt,
      text(it, font: ("Consolas", "KaiTi"))
    )
    text()[#h(0.0em)] // 用来使得块级元素后分段
  }

  show link: it => {
    set text(fill: blue)
    show-cn-fakebold(it)
  }

  let title = text(font: "SimHei", size: 18pt)[#title]

  align(center, text(17pt)[
    *#title*
  ])

  align(center)[
    #block(
      width: 80%,
    )[
      #grid(
        columns: (1fr, 1fr, 1fr),
        align(center)[
          *班级：*#underline()[#class] 
        ],
        align(center)[
          *学号：*#underline()[#student-id]
        ],
        align(center)[
          *姓名：*#underline(evade: false)[#author]
        ],
      )         
    ]
  ]

  body

}


