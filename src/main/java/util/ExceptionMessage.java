package util;

public enum ExceptionMessage {
    SAME_NAMES_MESSAGE("Имена игроков должны отличаться!"),
    NO_SUCH_PLAYER_MESSAGE("Игрок с таким именем не учавствовал в матчах!");

    public final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
