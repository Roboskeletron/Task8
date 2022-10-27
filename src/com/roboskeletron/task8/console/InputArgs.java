package com.roboskeletron.task8.console;

import java.util.Objects;

public class InputArgs{
    public final String inputFile;
    public final String outputFile;

    public  InputArgs(String[] args){
        switch (args.length){
            case 2 -> {
                var arg1 = parseArg(args[0]);
                var arg2 = parseArg(args[1]);

                if (arg1.type() == ArgType.OUTPUT){
                    inputFile = arg2.filename();
                    outputFile = arg1.filename();
                }
                else{
                    inputFile = arg1.filename();
                    outputFile = arg2.filename();
                }
            }
            case 4 ->{
                if (Objects.equals(args[0], "-o") && Objects.equals(args[2], "-i")) {
                    outputFile = args[1];
                    inputFile = args[3];
                }
                else if (Objects.equals(args[0], "-i") && Objects.equals(args[2], "-o")){
                    inputFile = args[1];
                    outputFile = args[3];
                }
                else
                    throw new IllegalArgumentException();
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private Argument parseArg(String arg){
        ArgType type = ArgType.NONE;
        String filename = arg;

        if (arg.startsWith("--input-file=")){
            type = ArgType.INPUT;
        }
        else if (arg.startsWith("–-output-file=")){

            type = ArgType.OUTPUT;
        }

        if (type!=ArgType.NONE){
            filename = arg.split("=")[1];
        }

        return new Argument(filename, type);
    }
}

enum ArgType{
    INPUT,
    OUTPUT,
    NONE
}

record Argument(String filename, ArgType type){

}