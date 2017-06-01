# Boolean parser

## Grammar

1. O->XO'
1. O'->or XO'
1. O'->epsilon
1. X->AX'
1. X'->xor AX'
1. X'->epsilon
1. A->TA'
1. A'->and TA'
1. A'->epsilon
1. T->\[a-zA-Z]
1. T->not T
1. T->( O )

## LL(1) table



|	  |  [a-zA-Z]  |  or  |  xor  |  and  |  (   |  )  |  not  |  epsilon  |
|:---:|:----------:|:----:|:-----:|:-----:|:----:|:---:|:-----:|:---------:|
|  O  |  1         |      |       |       |  1   |     |  1    |           |
|  O' |            |  2   |       |       |      |  3  |       |  3        |
|  X  |  4         |      |       |       |  4   |     |  4    |           |
|  X' |            |  6   |  5    |       |      |  6  |       |  6        |
|  A  |  7         |      |       |       |  7   |     |  7    |           |
|  A' |            |  9   |  9    |  8    |      |  9  |       |  9        |
|  T  |  10        |      |       |       |  12  |     |  11   |           |