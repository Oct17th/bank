package com.bank.view.console.util;

/**
 * 其实可以通过读取文件的途径来打印框框
 */
public class PrintUtil {
    private static final String methodBorder = "────────────────";
    private static final String accountBorder = "──────────────────────────────";
    //    private static String methodBorder;
    private static String supplementLine = "";

    enum WRAPPER_TYPE {//TODO 枚举的注释说明
        METHOD_INPUT, METHOD_ERROR, METHOD_INFO, METHOD_BEGIN, LOGIN, METHOD_END, INPUT, INFO, LOGOUT
    }

    private PrintUtil() {
    }

    public static void wrapper(String str, WRAPPER_TYPE type) {
        switch (type) {

            case METHOD_BEGIN:
                System.out.println(supplementLine + "┌" + methodBorder.substring(str.length(), methodBorder.length()) + str + "");
                break;
            case METHOD_INPUT:
                System.out.print(supplementLine + "│" + str);
                break;
            case METHOD_ERROR:
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│错误：" + str);
                System.out.println(supplementLine + "└" + methodBorder);
                break;
            case METHOD_INFO:
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│提示：" + str);
                System.out.println(supplementLine + "└" + methodBorder);
                break;
            case METHOD_END:
                System.out.println(supplementLine + "└" + methodBorder);
                break;

            case INPUT:
                System.out.print(supplementLine + str);
                break;
            case INFO:
                System.out.println(supplementLine + str);
                break;

            case LOGIN:
                System.out.println("┌" + accountBorder.substring(str.length(), accountBorder.length()) + str + "账户");
                supplementLine = "│\t";
                break;
            case LOGOUT:
                supplementLine = "";
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│提示：用户" + str + "退出账户管理！");
                System.out.println(supplementLine + "└" + accountBorder);
                break;
        }
    }

    public static void menu(String[] methodList) {
        StringBuffer index = new StringBuffer("│操作号\t│");
        StringBuffer method = new StringBuffer("│业务\t\t│");
        //设置单元格内容
        for (int i = 0; i < methodList.length; i++) {
            index.append(i + (methodList[i].length() > 2 ? "\t\t│" : "\t│"));//TODO 测试通过"\t\t\t│" : "\t\t│"
            method.append(methodList[i] + "\t│");
        }
        System.out.println(supplementLine + "┌" + drawLine(methodList, "┬") + "┐");
        System.out.println(supplementLine + index);
        System.out.println(supplementLine + "├" + drawLine(methodList, "┼") + "┤");
        System.out.println(supplementLine + method);
        System.out.println(supplementLine + "└" + drawLine(methodList, "┴") + "┘");
    }

    private static String drawLine(String[] mark, String separator) {
        int smallSize = 3;//TODO idea内console测试通过长度：7：11
        int bigSize = 7;
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < bigSize; j++) {
            stringBuffer.append("─");
        }
        for (int i = 0, cellLength; i < mark.length; i++) {
            stringBuffer.append(separator);
            cellLength = mark[i].length() > 2 ? bigSize : smallSize;//字符长度为4时，单元格长度为11个─；字符长度为2时，单元格长度为7个─；（忽略分隔符separator）
            for (int j = 0; j < cellLength; j++) {
                stringBuffer.append("─");
            }
        }
        return new String(stringBuffer);
    }

}
