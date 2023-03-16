package chess.view;

import chess.view.validator.InputRequest;
import chess.view.validator.InputValidator;
import chess.view.validator.StartValidator;
import chess.view.validator.ValidateType;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
                                                "> 게임 시작 : start\n" +
                                                "> 게임 종료 : end\n" +
                                                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final Scanner scanner = new Scanner(System.in);

    public void printStartChess() {
        System.out.println(START_MESSAGE);
    }

    public List<String> requestCommend(ValidateType validateType) {
        List<String> input = List.of(scanner.nextLine().split(" "));
        validate(input,List.of(validateType));
        return input;
    }

    private void validate(List<String> input,List<ValidateType> validateTypes){
        InputValidator validator = new StartValidator();
        validator.validate(new InputRequest(validateTypes,input));
    }
}
