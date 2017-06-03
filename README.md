# Boolean parser

Command line tool for boolean logic parsing.

Author: Patrik Valkovic  
Year: 2017

## Priority

Upper will be evaluated first

1. Not
1. And
1. Xor
1. Or

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

## Example run

Input:

```
first and (second or not sixth xor third or (first and not second)) or second xor (third and fifth) and not (fifth xor first)
```

Result:

```
or
|--and
|  |--first
|  `--or
|     |--or
|     |  |--second
|     |  `--xor
|     |     |--not
|     |     |  `--sixth
|     |     `--third
|     `--and
|        |--first
|        `--not
|           `--second
`--xor
   |--second
   `--and
      |--and
      |  |--third
      |  `--fifth
      `--not
         `--xor
            |--fifth
            `--first

Used variables: sixth,third,fifth,first,second

| sixth | third | fifth | first | second | Result |
|-------|-------|-------|-------|--------|--------|
| true  | true  | true  | true  | true   | true   |
| false | true  | true  | true  | true   | true   |
| true  | false | true  | true  | true   | true   |
| false | false | true  | true  | true   | true   |
| true  | true  | false | true  | true   | true   |
| false | true  | false | true  | true   | true   |
| true  | false | false | true  | true   | true   |
| false | false | false | true  | true   | true   |
| true  | true  | true  | false | true   | true   |
| false | true  | true  | false | true   | true   |
| true  | false | true  | false | true   | true   |
| false | false | true  | false | true   | true   |
| true  | true  | false | false | true   | true   |
| false | true  | false | false | true   | true   |
| true  | false | false | false | true   | true   |
| false | false | false | false | true   | true   |
| true  | true  | true  | true  | false  | true   |
| false | true  | true  | true  | false  | true   |
| true  | false | true  | true  | false  | true   |
| false | false | true  | true  | false  | true   |
| true  | true  | false | true  | false  | true   |
| false | true  | false | true  | false  | true   |
| true  | false | false | true  | false  | true   |
| false | false | false | true  | false  | true   |
| true  | true  | true  | false | false  | false  |
| false | true  | true  | false | false  | false  |
| true  | false | true  | false | false  | false  |
| false | false | true  | false | false  | false  |
| true  | true  | false | false | false  | false  |
| false | true  | false | false | false  | false  |
| true  | false | false | false | false  | false  |
| false | false | false | false | false  | false  |
```