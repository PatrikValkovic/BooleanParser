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



|	  |  [a-zA-Z]  |  or  |  xor  |  and  |  (  |  )  |  not  |  epsilon  |
|:---:|:----------:|:----:|:-----:|:-----:|:---:|:---:|:-----:|:---------:|
|  O  |            |      |       |       |     |     |       |           |
|  O' |            |      |       |       |     |     |       |           |
|  X  |            |      |       |       |     |     |       |           |
|  X' |            |      |       |       |     |     |       |           |
|  A  |            |      |       |       |     |     |       |           |
|  A' |            |      |       |       |     |     |       |           |
|  T  |            |      |       |       |     |     |       |           |