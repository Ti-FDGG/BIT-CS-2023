// TODO: 改为使用数组和字典来存储学生信息，而不是使用多个参数

#import "@preview/numbly:0.1.0":numbly

#let report-body(
  class_s1: "",
  title: "",
  student-id_s1: "", 
  name_s1: "",
  phone-number_s1: "",
  class_s2: "",
  title_s2: "",
  student-id_s2: "", 
  name_s2: "",
  phone-number_s2: "",
  body
) = {
  set text(11pt, font: "Microsoft YaHei")
  show table: set text(font: (
    (name: "Times New Roman", covers: "latin-in-cjk"), // 西文字体
    "SimSun" // 中文字体
  ), lang: "zh")
  show figure: set text(font: (
    (name: "Times New Roman", covers: "latin-in-cjk"), // 西文字体
    "SimSun" // 中文字体
  ), lang: "zh")
  show figure.where(
    kind: table
  ): set figure.caption(position: top) // 表格标题在上方

  set heading(numbering: numbly(
    "",
    "{2}.", 
    "{3:a})",
    "",
  ))
  show heading: it =>  {
      text(11pt)[#it]
      par()[#text()[#h(0.0em)]]
  }

  show raw.where(block: false): it => {
    h(2pt, weak: true)
    box(
      // fill: luma(230),
      radius: 1pt,
      outset: (y: 3pt), // 设置outset不会影响行布局，而inset则会使得box内部文字比行内其他文字要高
      text(it, font: ("Consolas", "KaiTi"), size: 10pt)
    )
    h(2pt, weak: true)
  }
  // show raw.where(block: true): it => {
  //   block(
  //     // fill: luma(230),
  //     width: 100%,
  //     // radius: 5pt,
  //     inset: 8pt,
  //     text(it, font: ("Consolas", "Noto Serif CJK SC"))
  //   )
  //   text()[#h(0.0em)] // 用来使得块级元素后分段
  // }
  show raw.where(block: true): code => {
    grid(
      columns: (auto, auto),
      column-gutter: 1em,
      row-gutter: par.leading,
      align: (right, raw.align),
      ..for line in code.lines {
        (
          text(fill: gray)[#line.number],
          text(line.body, font: ("Consolas", "Noto Serif CJK SC"))
        )
      },
    )
  } // 来源：https://github.com/typst/typst/issues/344#issuecomment-2669285516

  set par(leading: 1.5em, justify: true, first-line-indent: 2em)

  show figure.where(
    kind: table
  ): set par(leading: 0.8em)

  let title = [#title]

  align(center, text(17pt)[
      *#text(title, 14pt)* 
  ])


  line(length: 100%)
  grid(
    columns: (1fr, 1fr),
    align()[
      *组长：*#name_s1 \ // 学生1为组长
      *班级：*#class_s1 \
    ],
    align()[
      *学号：*#student-id_s1 \
      *手机：*#phone-number_s1 \
    ]
  )
  line(length: 100%)
  grid(
    columns: (1fr, 1fr),
    align()[
      *组员：*#name_s2 \
      *班级：*#class_s2 \
    ],
    align()[
      *学号：*#student-id_s2 \
      *手机：*#phone-number_s2 \
    ]
  )
  line(length: 100%)

  body
}

