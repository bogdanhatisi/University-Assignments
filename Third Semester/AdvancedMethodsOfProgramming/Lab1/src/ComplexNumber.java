public class ComplexNumber {
    private int real;
    private int imaginary;

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public int getReal() {
        return real;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }


    public ComplexNumber addition(ComplexNumber number)
    {
        int newReal = this.real + number.getReal();
        int newImag = this.imaginary+number.getImaginary();
        ComplexNumber result = new ComplexNumber(newReal,newImag);

        return result;
    }

    public ComplexNumber substraction(ComplexNumber number)
    {

        int newReal = this.real - number.getReal();
        int newImag = this.imaginary - number.getImaginary();
        ComplexNumber result = new ComplexNumber(newReal,newImag);

        return result;
    }

    public ComplexNumber multiplication(ComplexNumber number)
    {
        int newReal = (this.real * number.getReal()) - (this.imaginary * number.getImaginary());
        int newImag = (this.real * number.getImaginary()) + (this.imaginary* number.getReal());

        ComplexNumber result = new ComplexNumber(newReal,newImag);

        return result;
    }

    public ComplexNumber conjugate()
    {
        int conjugateReal = this.real;
        int conjugateImag = this.imaginary;
        ComplexNumber conjugateNumber = new ComplexNumber(conjugateReal,conjugateImag);

        return conjugateNumber;
    }


    public ComplexNumber division(ComplexNumber number) {
        ComplexNumber conjugateDenom = number.conjugate();
        int divideBy = conjugateDenom.real*conjugateDenom.real + conjugateDenom.imaginary*conjugateDenom.imaginary;
        int newReal = this.real * conjugateDenom.getReal() + this.imaginary * conjugateDenom.getImaginary();
        newReal = newReal / divideBy;
        int newImag =  this.imaginary*conjugateDenom.getReal() - this.real*conjugateDenom.getImaginary();
        newImag = newImag / divideBy;

        ComplexNumber result = new ComplexNumber(newReal,newImag);

        return result;

    }



}
