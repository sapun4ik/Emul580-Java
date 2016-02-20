package controller;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum CommandsBtn {
        START("Старт"),
        STOP("Стоп"),
        REDUCE("Уменьшить"),
        FINDING_ADDRESSES("Отыскание адреса"),
        FINDING_REGISTER("Отыскание регистра"),
        STEP_TEAM("Шаг команды"),
        PROGRAM_COUNTER("Программный счётчик"),
        RELOADING("Перезагрузить"),
        RECORD("Записать"),
        STEP_CYCLE("Шаг цикла");

        public final String text;
    CommandsBtn(final String text){
            this.text = text;
        }
}
