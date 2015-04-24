package com.itextpdf.tool.xml.examples.css.div;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFilesImpl;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.examples.SampleTest;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class DivSampleTest extends SampleTest {
    @Override
    protected void makePdf(String outPdf) throws Exception {
        Document doc = new Document(PageSize.A4_LANDSCAPE);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream(outPath + outPdf));
        doc.setMargins(0, 0, 0, 0);
        doc.open();


        CssFilesImpl cssFiles = new CssFilesImpl();
        cssFiles.add(XMLWorkerHelper.getCSS(SampleTest.class.getResourceAsStream("sampleTest.css")));
        StyleAttrCSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);
        HtmlPipelineContext hpc = new HtmlPipelineContext(new CssAppliersImpl(new XMLWorkerFontProvider(SampleTest.class.getResource("fonts").getPath())));
        hpc.setAcceptUnknown(true).autoBookmark(true).setTagFactory(Tags.getHtmlTagProcessorFactory());
        hpc.setImageProvider(new SampleTestImageProvider());
        HtmlPipeline htmlPipeline = new HtmlPipeline(hpc, new PdfWriterPipeline(doc, pdfWriter));
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(true, worker, Charset.forName("UTF-8"));
        p.parse(new FileInputStream(inputHtml), Charset.forName("UTF-8"));
        doc.close();
    }
}
