class PCR extends Test {
    private final String result;

    PCR(String result) {
        super("P");
        this.result = result;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isPositive() {
        return result.equals("alpha") ||
               result.equals("beta") ||
               result.equals("delta") ||
               result.equals("omicron");
    }
}
