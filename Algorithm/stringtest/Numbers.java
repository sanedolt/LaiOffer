package com.laioffer.Algorithm.stringtest;

public class Numbers {
    /*
    163. Add Binary
    Given two binary strings, return their sum (also a binary string).
    Input: a = “11”
           b = “1”
    Output: “100”
    */
    public String addBinary(String a, String b) { // 163
        if (a==null || b==null) {return null;}
        int la=a.length(),lb=b.length();
        int ls=Math.max(la,lb),carry=0;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<ls;i++) {
            char va = i<la?a.charAt(la-1-i):0;
            char vb = i<lb?b.charAt(lb-1-i):0;
            int sum = (va=='1'?1:0)+(vb=='1'?1:0)+carry;
            sb.append(String.valueOf(sum%2));
            carry=sum/2;
        }
        if (carry==1) {sb.append('1');}
        return new String(sb.reverse());
        // Write your solution here
    }

    public String divide(int a, int b) {
        if (b == 0) {return "NaN";}
        if (a == 0) {return "0";}
        String result = String.valueOf(a/b);
        if (a%b==0) {return result;}
        result=result+".";
        System.out.println(result);
        double c = (double) a / b;
        String temp = String.valueOf(c);
        a=Math.abs(a);
        b=Math.abs(b);
        int t = b; // if the only factors of b are 2&5, then there is no chance of repeated decimal part
        while (t % 2 == 0) {t /= 2;}
        while (t % 5 == 0) {t /= 5;}
        if (t == 1) {return temp;}
        double d = (double) a / (b/t);
        System.out.println(t);
        System.out.println(d);
        int m=1,q=(int) d;
        while ((d*m)!=(int) (d*m)) {
            m*=10;
            a*=10;
            q*=10;
            result=result+String.valueOf((int) d*m-q);
        }
        int r=a%b, rr=r;
        result=result+"(";
        System.out.println(result+" "+r);
        for (int i=0;i<b;i++) {
            if (rr==r && i>0) {
                result = result + ")";
                return result;
            }
            System.out.println(rr*10/b);
            result=result+String.valueOf(rr*10/b);
            rr=(rr*10)%b;
        }
        return temp;
        // Write your solution here
    }
    /*
    225. String To Integer
    Implement atoi to convert a string to an integer. Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases. Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
    If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, return INT_MAX (2147483647) if it is positive, or INT_MIN (-2147483648) if it is negative.
     */
    public boolean atoi1(String str) { //225
        str = str.trim();
        int l = str.length();
        if (l == 0) {
            return false;
        }
        boolean seenNumber = false;
        boolean seenSignBeforeE = false;
        boolean seenE = false;
        boolean seenPoint = false;
        boolean seenNumberAfterE = false;
        boolean seenSignAfterE = false;
        for (int i = 0; i < l; i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                if (((seenPoint || seenNumber) && !seenE) || seenNumberAfterE) {
                    return false;
                } // should before number no matter before or after E
                if (seenSignAfterE || !seenE && seenSignBeforeE) {
                    return false;
                } // double sign
                if (seenE) {
                    seenSignAfterE = true;
                } else {
                    seenSignBeforeE = true;
                }
            } else if (c >= '0' && c <= '9') {
                seenNumber = true;
                if (seenE) {
                    seenNumberAfterE = true;
                }
            } else if (c == 'e' || c == 'E') {
                if (seenE) {
                    return false;
                } // double E
                if (!seenNumber) {
                    return false;
                } // no accurate digit
                seenE = true;
            } else if (c == '.') {
                if (seenE || seenPoint) {
                    return false;
                } // has to be in number before E
                seenPoint = true;
            } else { // other letters
                return false;
            }
        }
        if (seenE && !seenNumberAfterE) {
            return false;
        }
        return seenNumber;
    }
    public int atoi2(String str) { // 225
        str=str.trim();
        int l=str.length();
        if (l==0) {return 0;}
        int sign=0;
        long tot=0L;
        boolean beg=true;
        for (int i=0;i<l;i++) {
            if (beg && (str.charAt(i)=='0' || str.charAt(i)==' ')) {continue;}
            if (!beg && (str.charAt(i)<'0' || str.charAt(i)>'9')) {
                return sign*((int) tot);
            }
            if (beg) {
                beg=false;
                if (str.charAt(i)=='-') {
                    sign=-1;
                    continue;
                } else if (str.charAt(i)<'0' || str.charAt(i)>'9') {
                    return 0;
                } else {
                    sign=1;
                }
            } // end beg
            int cur=str.charAt(i)-'0';
            tot=tot*10+cur;
            if (sign==1) {
                if (tot>Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else { //sign==-1
                if (tot>-(long)Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return sign*((int) tot);
        // Write your solution here
    }
    public String numberToWords(int num) {
        if (num<0) {return "";}
        if (num==0) {return "Zero";}
        String[] single = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve"};
        String[] tens = {"dummyZero","dummyTen","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] unit = {"","Thousand","Million","Trillion"};
        String[] prefix = {""," "};
        String result="";
        int index=0, block=0;
        System.out.println(result);
        for (int i=3;i>=0;i--) {
            int temp = (int) Math.pow(10,i*3);
            num-=block*1000*temp;
            block = num / temp;
            System.out.println(block);
            int h = block / 100, t = block / 10 - h * 10, o = block % 10;
            if (h > 0) {
                result = result + prefix[index] + single[h] + " Hundred";
                index=1;
            }
            if (t > 1) {
                result = result + " " + tens[t];
            } else if (t == 1) {
                if (block % 100 < 13) {
                    result = result + prefix[index] + single[block % 100];
                    index=1;
                } else {
                    result = result + prefix[index] + single[block%10]+"teen";
                    index=1;
                }
            } else { // t==0, do nothing
            }
            if (t!=1 && o > 0) {
                result = result + prefix[index] + single[o];
                index=1;
            }
            if (block>0) {result=result+prefix[i>0?1:0]+unit[i];}
            System.out.println(result);
        }
        return result;
        // Write your solution here
    }
    /*
    224. Reverse Integer
    Reverse digits of an integer.
    Assumptions
    If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
    Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
    For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
    */
    public int reverse(int x) { // 224
        long result=0;
        boolean sign=x>=0;
        int a=Math.abs(x);
        while (a>=1) {
            int b=a%10;
            result=result*10+b;
            if (sign && result>Integer.MAX_VALUE || !sign && result>(-(long) Integer.MIN_VALUE)) {return 0;}
            a/=10;
        }
        return (int) result*(sign?1:-1);
        // Write your solution here
    }
    /*
    226. Multiply Strings
    Given two numbers represented as strings, return multiplication of the numbers as a string. The numbers can be arbitrarily large and are non-negative.
    Example
    Input: "19", "20"
    Output: "380"
     */
    public String multiply(String num1, String num2) { // 226
        if (num1=="" || num2=="") {return "";}
        if (num1.equals("0") || num2.equals("0")) {return "0";}
        int l1=num1.length();
        int l2=num2.length();
        char[] input1 = num1.toCharArray();
        char[] input2 = num2.toCharArray();
        reverse(input1);reverse(input2);
        StringBuilder result = new StringBuilder();
        int dig=-1,sum=0;
        while (++dig<l1+l2-1) { // each time count the 2 digits from 2 number which will contribute to dig digit
            for (int i=0;i<l1;i++) {
                int j=dig-i;
                if (j<0 || j>l2-1) {continue;}
                sum+=(input1[i]-'0')*(input2[j]-'0');
            }
            result.append((char) (sum%10+'0'));
            sum/=10;
        }
        if (sum>0) {result.append((char) (sum+'0'));}
        return result.reverse().toString();
        // Write your solution here
    }
    private void reverse(char[] array) {
        int left=0,right=array.length-1;
        while (left<right) {
            swap(array,left++,right--);
        }
    }
    private void swap(char[] array, int left, int right) {
        char tmp = array[left];
        array[left]=array[right];
        array[right]=tmp;
    }
    /*
    235. Count and Say
    Given a sequence of number: 1, 11, 21, 1211, 111221, …
    The rule of generating the number in the sequence is as follow:
    1 is "one 1" so 11.
    11 is "two 1s" so 21.
    21 is "one 2 followed by one 1" so 1211.
    Find the nth number in this sequence.
     */
    public String countAndSay(int n) { // 235
        if (n<1 || n>=30) {return "";}
        String num="1";
        for (int i=2;i<=n;i++) {
            int len=num.length(),left=0;
            char c1 = num.charAt(left);
            StringBuilder ans = new StringBuilder();
            for (int right=1;right<=len;right++) {
                char c2 = right==len?(char)(c1+1):num.charAt(right);
                if (c2!=c1) {
                    ans.append(String.valueOf(right-left));
                    ans.append(c1);
                    c1=c2;
                    left=right;
                }
            }
            num=ans.toString();
        }
        return num;
        // Write your solution here
    }
    /*
    420. Additive Number
    Additive number is a string whose digits can form additive sequence.
    A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
    For example:
    "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
    1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
    "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
    1 + 99 = 100, 99 + 100 = 199
    Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
    Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
     */
    public boolean isAdditiveNumber(String num) { // 420
        if (num==null || num.length()==0) {return false;}
        int len=num.length();
        if (len<3) {return false;}
        int imax=num.charAt(0)=='0'?0:(len+1)/2-2;
        for (int i=0;i<=imax;i++) { // search for the last index of 1st number
            int la=i+1;
            int jmax=num.charAt(la)=='0'?la:i+Math.min(len-la*2,(len+1)/2-1);
            // first case means 1st and 3rd having same length, second case means 1st and 2nd having same length
            for (int j=la;j<=jmax;j++) {
                int left=j+1,right=j+Math.max(la,j-i); // left & right bound of 3rd number
                int ia=0,ib=la,lc=-1;
                while (right<len) {
                    lc=isEqual(num,ia,ib,left);
                    if (lc<0) {break;}
                    ia=ib;
                    ib=left;
                    left+=lc;
                    right=left-1+lc; // potential right bound of next number
                    if (left==len) {return true;}
                } // end while
            }
        }
        return false;
        // Write your solution here
    }
    private int isEqual(String num, int ia, int ib, int ic) {
        int la=ib-ia;
        int lb=ic-ib;
        // no advanced digit
        int lc=Math.max(la,lb);
        if (num.charAt(ic)=='0' && lc>1) {return -1;}
        if (ic+lc>num.length()) {return -1;}
        boolean result=true; int carry=0;
        for (int i=lc-1;i>=0;i--) {
            int va=ib+i>=ia+lc?num.charAt(ib-lc+i)-'0':0;
            int vb=ic+i>=ib+lc?num.charAt(ic-lc+i)-'0':0;
            if ((va+vb+carry)%10!=(num.charAt(ic+i)-'0')) {result=false;break;}
            carry=(va+vb+carry)/10;
        }
        if (result && carry==0) {return lc;}
        // with advanced digit;
        if (++lc>1 && num.charAt(ic)=='0') {return -1;}
        if (ic+lc>num.length()) {return -1;}
        result=true; carry=0;
        for (int i=lc-1;i>=0;i--) {
            int va=ib+i>=ia+lc?num.charAt(ib-lc+i)-'0':0;
            int vb=ic+i>=ib+lc?num.charAt(ic-lc+i)-'0':0;
            if ((va+vb+carry)%10!=(num.charAt(ic+i)-'0')) {result=false;break;}
            carry=(va+vb+carry)/10;
        }
        return result?lc:-1;
    }
    public static void main(String[] args) {
        Numbers solution = new Numbers();
        int a = -2204;
        int b = -110;
        System.out.println(solution.divide(a, b));
        String d = "    -923 67   ";
        System.out.println(solution.atoi2(d));
        System.out.println(solution.numberToWords(12345717));
        System.out.println(solution.reverse(-2147483648));
        String e = "19";
        String f = "20";
        System.out.println(solution.multiply(e,f));
    }
}
