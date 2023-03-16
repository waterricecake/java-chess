package chess.view.validator;

public class StartValidator implements InputValidator{
    private static final String START_INPUT = "start";
    private static final String END_INPUT = "end";
    private static final String EXCEPTION_MESSAGE = "start나 end를 입력해야 합니다.";
    private InputValidator next;

    private void setNext() {
        this.next = new PlayValidator();
    }

    @Override
    public void validate(InputRequest inputRequest) {
        if(inputRequest.notValidate(ValidateType.START)){
            setNext();
            next.validate(inputRequest);
            return;
        }
        inputRequest.getValue()
                .stream()
                .filter(input -> input.equals(START_INPUT)||input.equals(END_INPUT))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(EXCEPTION_MESSAGE));
    }
}
