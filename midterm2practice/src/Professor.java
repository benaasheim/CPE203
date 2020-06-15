public class Professor extends Person
{
    private int mortgage; private boolean tenure;
    public Professor(int mortgage, boolean tenure, String name,
                     int account, int legs)
    {
        super(name, account, legs); this.mortgage = mortgage; this.tenure = tenure;
    }
    public int getMortgage() { return mortgage; }
    public boolean hasTenure() { return tenure; }
    public String toString()
    {
        String s = super.toString() + " and a Professor object with a " + mortgage + " dollar mortgage and ";
        if (tenure)
            s += "tenure.";
        else
            s += "no tenure.";
        return s;
    }

    public String greet(Professor p) { return "Good day."; }
    public String greet(Student s) { return "How can I help you? "; }
    public String greet(Person p) { return "Hello. "; }
    public boolean equals(Object o)
        {
            return  super.equals(o) &&
                    mortgage == ((Professor)o).mortgage &&
                    ((Professor)o).tenure == tenure;
        }

    public void getPaid(int amount)
        {
            int mortgagePayment = (int)(amount * .25);
            mortgagePayment = mortgagePayment > mortgage ?
                    mortgage : mortgagePayment;
            mortgage -= mortgagePayment;
            super.getPaid(amount-mortgagePayment);
        }
}