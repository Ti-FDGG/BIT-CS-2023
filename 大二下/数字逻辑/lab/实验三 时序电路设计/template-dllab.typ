#import "@preview/numbly:0.1.0":numbly

#let report-body(
  class: "",
  student-id: "", 
  author: "",
  title: "",
  phone-number: "",
  body
) = {
  set text(11pt, font: "Microsoft YaHei")
  show table: set text(font: "Times New Roman")
  show figure: set text(font: "")
  show figure.where(
    kind: table
  ): set figure.caption(position: top)

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

  show raw.where(block: true): it => block(
      fill: luma(230),
      width: 90%,
      it
  )

  set par(leading: 0.8em, justify: true, first-line-indent: 2em)

  let title = [#title]

  align(center, text(17pt)[
      *#text(title, 14pt)* 
  ])


  line(length: 100%)
  grid(
    columns: (1fr, 1fr),
    align()[
      *姓名：*#author \
      *班级：*#class \
    ],
    align()[
      *学号：*#student-id \
      *手机：*#phone-number \
    ]
  )
  line(length: 100%)

  body
}

