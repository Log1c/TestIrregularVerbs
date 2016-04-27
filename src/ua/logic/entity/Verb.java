package ua.logic.entity;

public class Verb {
    private final String firstForm;
    private final String secondForm;
    private final String thirdForm;

    public Verb(String firstForm, String secondForm, String thirdForm) {
        this.firstForm = firstForm;
        this.secondForm = secondForm;
        this.thirdForm = thirdForm;
    }

    public String getFirstForm() {
        return firstForm;
    }

    public String getSecondForm() {
        return secondForm;
    }

    public String getThirdForm() {
        return thirdForm;
    }

    @Override
    public String toString() {
        return firstForm
                + "; " + secondForm
                + "; " + thirdForm
                ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verb verb = (Verb) o;

        if (!firstForm.equals(verb.firstForm)) return false;
        if (!secondForm.equals(verb.secondForm)) return false;
        return thirdForm.equals(verb.thirdForm);

    }

    @Override
    public int hashCode() {
        return firstForm.hashCode();
    }
}
