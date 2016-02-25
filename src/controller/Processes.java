package controller;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum Processes {
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
        public static Processes getProcess(String name){
                for (Processes p :Processes.values()) {
                        if (p.text.equals(name)) return p;
                }
                return null;
        }
    Processes(final String text){
            this.text = text;
        }
}
